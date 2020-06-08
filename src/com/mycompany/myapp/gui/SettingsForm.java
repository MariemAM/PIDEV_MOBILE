/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Log;
import com.codename1.io.Util;
import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import com.codename1.media.MediaRecorderBuilder;
import com.codename1.ui.Button;
import com.codename1.ui.CN;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.animations.CommonTransitions;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.util.EasyThread;
import com.codename1.util.OnComplete;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author user16
 */

public class SettingsForm extends ProfilForm  {
     private static final EasyThread countTime = EasyThread.start("countTime");
 public SettingsForm() {
   
       super.addSideMenu();
       
        Container hi = new Container( BoxLayout.y());
        hi.add(new SpanLabel("Example of recording and playback audio using the Media, MediaManager and MediaRecorderBuilder APIs"));
        hi.add(recordAudio((String filePath) -> {
            ToastBar.showInfoMessage("Do something with the recorded audio file: " + filePath);
        }));
        add(hi);
    }

    public static Component recordAudio(OnComplete<String> callback) {
        try {
            // mime types supported by Android: audio/amr, audio/aac, audio/mp4
            // mime types supported by iOS: audio/mp4, audio/aac, audio/m4a
            // mime type supported by Simulator: audio/wav
            // more info: https://www.iana.org/assignments/media-types/media-types.xhtml

            List<String> availableMimetypes = Arrays.asList(MediaManager.getAvailableRecordingMimeTypes());
            String mimetype;
            if (availableMimetypes.contains("audio/aac")) {
                // Android and iOS
                mimetype = "audio/aac";
            } else if (availableMimetypes.contains("audio/wav")) {
                // Simulator
                mimetype = "audio/wav";
            } else {
                // others
                mimetype = availableMimetypes.get(0);
            }
            String fileName = "audioExample." + mimetype.substring(mimetype.indexOf("/") + 1);
            String output = FileSystemStorage.getInstance().getAppHomePath() + "/" + fileName;
            // https://tritondigitalcommunity.force.com/s/article/Choosing-Audio-Bitrate-Settings
            MediaRecorderBuilder options = new MediaRecorderBuilder()
                    .mimeType(mimetype)
                    .path(output)
                    .bitRate(64000)
                    .samplingRate(44100);
            Media[] microphone = {MediaManager.createMediaRecorder(options)};
            Media[] speaker = {null};

            Container recordingUI = new Container(BoxLayout.y());
            Label time = new Label("0:00");
            Button recordBtn = new Button("", FontImage.MATERIAL_FIBER_MANUAL_RECORD, "Button");
            Button playBtn = new Button("", FontImage.MATERIAL_PLAY_ARROW, "Button");
            Button stopBtn = new Button("", FontImage.MATERIAL_STOP, "Button");
            Button sendBtn = new Button("Send");
            sendBtn.setEnabled(false);
            Container buttons = GridLayout.encloseIn(3, recordBtn, stopBtn,sendBtn);
            recordingUI.addAll(FlowLayout.encloseCenter(time), FlowLayout.encloseCenter(buttons));

            recordBtn.addActionListener(l -> {
                try {
                    // every time we have to create a new instance of Media to make it working correctly (as reported in the Javadoc)
                    microphone[0] = MediaManager.createMediaRecorder(options);
                    if (speaker[0] != null && speaker[0].isPlaying()) {
                        return; // do nothing if the audio is currently recorded or played
                    }
                    recordBtn.setEnabled(false);
                    sendBtn.setEnabled(true);
                    Log.p("Audio recording started", Log.DEBUG);
                    if (buttons.contains(playBtn)) {
                        buttons.replace(playBtn, stopBtn, CommonTransitions.createEmpty());
                        buttons.revalidateWithAnimationSafety();
                    }
                    if (speaker[0] != null) {
                        speaker[0].pause();
                    }

                    microphone[0].play();
                    startWatch(time);
                } catch (IOException ex) {
                    Log.p("ERROR recording audio", Log.ERROR);
                    Log.e(ex);
                }
            });

            stopBtn.addActionListener(l -> {
                if (!microphone[0].isPlaying() && (speaker[0] == null || !speaker[0].isPlaying())) {
                    return; // do nothing if the audio is NOT currently recorded or played
                }
                recordBtn.setEnabled(true);
                sendBtn.setEnabled(true);
                Log.p("Audio recording stopped");
                if (microphone[0].isPlaying()) {
                    microphone[0].pause();
                } else if (speaker[0] != null) {
                    speaker[0].pause();
                } else {
                    return; 
                }
                stopWatch(time);
                if (buttons.contains(stopBtn)) {
                    buttons.replace(stopBtn, playBtn, CommonTransitions.createEmpty());
                    buttons.revalidateWithAnimationSafety();
                }
                if (FileSystemStorage.getInstance().exists(output)) {
                    Log.p("Audio saved to: " + output);
                } else {
                    ToastBar.showErrorMessage("Error recording audio", 5000);
                    Log.p("ERROR SAVING AUDIO");
                }
            });

            playBtn.addActionListener(l -> {
                // every time we have to create a new instance of Media to make it working correctly (as reported in the Javadoc)
                if (microphone[0].isPlaying() || (speaker[0] != null && speaker[0].isPlaying())) {
                    return; // do nothing if the audio is currently recorded or played
                }
                recordBtn.setEnabled(false);
                sendBtn.setEnabled(true);
                if (buttons.contains(playBtn)) {
                    buttons.replace(playBtn, stopBtn, CommonTransitions.createEmpty());
                    buttons.revalidateWithAnimationSafety();
                }
                if (FileSystemStorage.getInstance().exists(output)) {
                    try {
                        speaker[0] = MediaManager.createMedia(output, false, () -> {
                            // callback on completation
                            recordBtn.setEnabled(true);
                            if (speaker[0].isPlaying()) {
                                speaker[0].pause();
                            }
                            stopWatch(time);
                            if (buttons.contains(stopBtn)) {
                                buttons.replace(stopBtn, playBtn, CommonTransitions.createEmpty());
                                buttons.revalidateWithAnimationSafety();
                            }
                        });
                        speaker[0].play();
                        startWatch(time);
                    } catch (IOException ex) {
                        Log.p("ERROR playing audio", Log.ERROR);
                        Log.e(ex);
                    }
                }
            });

            sendBtn.addActionListener(l -> {
                if (microphone[0].isPlaying()) {
                    microphone[0].pause();
                }
                if (speaker[0] != null && speaker[0].isPlaying()) {
                    speaker[0].pause();
                }
                if (buttons.contains(stopBtn)) {
                    buttons.replace(stopBtn, playBtn, CommonTransitions.createEmpty());
                    buttons.revalidateWithAnimationSafety();
                }
                stopWatch(time);
                recordBtn.setEnabled(true);
                callback.completed(output);
                   
            });

            return FlowLayout.encloseCenter(recordingUI);

        } catch (IOException ex) {
            Log.p("ERROR recording audio", Log.ERROR);
            Log.e(ex);
            return new Label("Error recording audio");
        }
        

    }

    private static void startWatch(Label label) {
        label.putClientProperty("stopTime", Boolean.FALSE);
        countTime.run(() -> {
            long startTime = System.currentTimeMillis();
            while (label.getClientProperty("stopTime") == Boolean.FALSE) {
                // the sleep is every 200ms instead of 1000ms to make the app more reactive when stop is tapped
                Util.sleep(200);
                int seconds = (int) ((System.currentTimeMillis() - startTime) / 1000);
                String min = (seconds / 60) + "";
                String sec = (seconds % 60) + "";
                if (sec.length() == 1) {
                    sec = "0" + sec;
                }
                String newTime = min + ":" + sec;
                if (!label.getText().equals(newTime)) {
                    CN.callSerially(() -> {
                        label.setText(newTime);
                        if (label.getParent() != null) {
                            label.getParent().revalidateWithAnimationSafety();
                        }
                    });
                }
            }
        });
    }

    private static void stopWatch(Label label) {
        label.putClientProperty("stopTime", Boolean.TRUE);
    }
}
    
    

 