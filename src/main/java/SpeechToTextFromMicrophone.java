import com.google.api.gax.rpc.ClientStream;
import com.google.api.gax.rpc.ResponseObserver;
import com.google.api.gax.rpc.StreamController;
import com.google.cloud.speech.v1.*;
import com.google.protobuf.ByteString;

import javax.sound.sampled.*;
import javax.sound.sampled.DataLine.Info;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class SpeechToTextFromMicrophone {
	// backend class that runs google speech-text API
	/*
	 * Parts of this code falls under the copyright information listed below // *
	 * Copyright 2018 Google Inc. // * // * Licensed under the Apache License,
	 * Version 2.0 (the "License"); // * you may not use this file except in
	 * compliance with the License. // * You may obtain a copy of the License at //
	 * * // * http://www.apache.org/licenses/LICENSE-2.0 // * // * Unless required
	 * by applicable law or agreed to in writing, software // * distributed under
	 * the License is distributed on an "AS IS" BASIS, // * WITHOUT WARRANTIES OR
	 * CONDITIONS OF ANY KIND, either express or implied. // * See the License for
	 * the specific language governing permissions and // * limitations under the
	 * License. //
	 */
	private volatile boolean shouldStop;
	private ArrayList<String> transcriptionRaw = new ArrayList<>();
	private ArrayList<Long> timesRaw = new ArrayList<>();
	private ArrayList<StreamingRecognizeResponse> responses = new ArrayList<>();
	
	/*public void onSplit() {
		for (StreamingRecognizeResponse response : responses) {
			StreamingRecognitionResult result = response.getResultsList().get(0);
			SpeechRecognitionAlternative alternative = result.getAlternativesList().get(0);
			String transcripts = String.format("%s", alternative.getTranscript());
			transcriptionRaw.add(transcripts);
		}
	}*/

	public void streamingMicRecognize() throws Exception {
		SpeechClient client = SpeechClient.create();
		long[] startTimeSplit = { System.currentTimeMillis() };
		ResponseObserver<StreamingRecognizeResponse> responseObserver 
			= new ResponseObserver<StreamingRecognizeResponse>() {
			public void onStart(StreamController controller) {
			}

			public void onResponse(StreamingRecognizeResponse response) {
				responses.add(response);
				long split = System.currentTimeMillis() - startTimeSplit[0];
				timesRaw.add(split);
				startTimeSplit[0] = System.currentTimeMillis();

			}

			public void onComplete() {
				for (StreamingRecognizeResponse response : responses) {
					StreamingRecognitionResult result = response.getResultsList().get(0);
					SpeechRecognitionAlternative alternative = result.getAlternativesList().get(0);
					System.out.printf("Transcript : %s\n", alternative.getTranscript());
					String transcripts = String.format("%s", alternative.getTranscript());
					transcriptionRaw.add(transcripts);
				}

			}

			public void onError(Throwable t) {
				System.out.println(t);
			}
		};

		ClientStream<StreamingRecognizeRequest> clientStream = client.streamingRecognizeCallable()
				.splitCall(responseObserver);

		RecognitionConfig recognitionConfig = RecognitionConfig.newBuilder()
				.setEncoding(RecognitionConfig.AudioEncoding.LINEAR16).setLanguageCode("en-US")
				.setSampleRateHertz(16000).setModel("Latest_long").build();
		StreamingRecognitionConfig streamingRecognitionConfig = StreamingRecognitionConfig.newBuilder()
				.setConfig(recognitionConfig).build();

		StreamingRecognizeRequest request = StreamingRecognizeRequest.newBuilder()
				.setStreamingConfig(streamingRecognitionConfig).build(); // The first request in a streaming call
																			// has to be a config

		clientStream.send(request);
		// SampleRate:16000Hz, SampleSizeInBits: 16, Number of channels: 1, Signed:
		// true,
		// bigEndian: false
		AudioFormat audioFormat = new AudioFormat(16000, 16, 1, true, false);
		DataLine.Info targetInfo = new Info(TargetDataLine.class, audioFormat); // Set the system information to
																				// read from the microphone audio
																				// stream

		if (!AudioSystem.isLineSupported(targetInfo)) {
			System.out.println("Microphone not supported");
			System.exit(0);
		}
		// Target data line captures the audio stream the microphone produces.
		TargetDataLine targetDataLine = (TargetDataLine) AudioSystem.getLine(targetInfo);
		targetDataLine.open(audioFormat);
		targetDataLine.start();
		System.out.println("Start speaking");
		long startTime = System.currentTimeMillis();
		// Audio Input Stream
		AudioInputStream audio = new AudioInputStream(targetDataLine);
		while (true) {
			long estimatedTime = System.currentTimeMillis() - startTime;
			byte[] data = new byte[6400];
			audio.read(data);

			if (shouldStop) {
				break;
			}
			if (estimatedTime > 15000) {
				// this.onSplit();
				startTime = System.currentTimeMillis();
				// break;
			}

			request = StreamingRecognizeRequest.newBuilder().setAudioContent(ByteString.copyFrom(data)).build();
			clientStream.send(request);
		}
		targetDataLine.stop();
		targetDataLine.close();
		audio.close();
		// proper termination of client contributed by Sam
		// (previously client would keep running in a separate thread,
		// eventually printing OUT_OF_RANGE)
		
		// give it some time
		client.shutdown();
		client.awaitTermination(1, TimeUnit.SECONDS);
		responseObserver.onComplete();
		// shutdownNow causes an exception to be printed to stdout, 
		// but is the quickest way to stop without waiting for OUT_OF_RANGE
		client.shutdownNow();
		client.close();
		
		System.out.println(transcriptionRaw);
		System.out.println(timesRaw);

	}

	public ArrayList<Long> getTimesRaw() {
		return timesRaw;
	}

	public ArrayList<String> getTranscriptionRaw() {
		return transcriptionRaw;
	}

	public void queueStop() {
		shouldStop = true;
	}

	public void start() throws Exception {
		shouldStop = false;
		this.streamingMicRecognize();
		System.out.println("done");
	}
}
