import javax.sound.sampled.*;

public class SoundManager {

    private Clip bgmClip;

    // เก็บเป็น 0 - 100
    private int bgmVolume = 25;
    private int sfxVolume = 50;

    private boolean muted = false;

    // =====================================================
    // BGM
    // =====================================================

    public void playBGM(String path) {
        try {
            stopBGM();

            AudioInputStream audioStream =
                    AudioSystem.getAudioInputStream(
                            getClass().getResource(path)
                    );

            bgmClip = AudioSystem.getClip();
            bgmClip.open(audioStream);
            audioStream.close();
            applyVolume(bgmClip, bgmVolume);

            bgmClip.loop(Clip.LOOP_CONTINUOUSLY);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stopBGM() {
        if (bgmClip != null) {
            bgmClip.stop();
            bgmClip.close();
            bgmClip = null;
        }
    }

    public void pauseBGM() {
        if (bgmClip != null && bgmClip.isRunning()) {
            bgmClip.stop();
        }
    }

    public void resumeBGM() {
        if (bgmClip != null && !bgmClip.isRunning()) {
            bgmClip.start();
        }
    }

    // =====================================================
    // SFX
    // =====================================================

    public void playSFX(String path) {
        try {
            AudioInputStream audioStream =
                    AudioSystem.getAudioInputStream(
                            getClass().getResource(path)
                    );

            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            audioStream.close();
            applyVolume(clip, sfxVolume);

            clip.start();

            // ป้องกัน memory leak
            clip.addLineListener(event -> {
                if (event.getType() == LineEvent.Type.STOP) {
                    clip.close();
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // =====================================================
    // Volume Control
    // =====================================================

    public void setBGMVolume(int volume) {
        bgmVolume = Math.max(0, Math.min(100, volume));

        if (bgmClip != null) {
            applyVolume(bgmClip, bgmVolume);
        }
    }

    public void setSFXVolume(int volume) {
        sfxVolume = Math.max(0, Math.min(100, volume));
    }

    public int getBGMVolume() {
        return bgmVolume;
    }

    public int getSFXVolume() {
        return sfxVolume;
    }

    public void toggleMute() {
        muted = !muted;

        if (bgmClip != null) {
            applyVolume(bgmClip, bgmVolume);
        }
    }

    // =====================================================
    // Internal Volume Logic
    // =====================================================

    private void applyVolume(Clip clip, int volume) {
        if (clip == null) return;

        try {
            FloatControl gainControl =
                    (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);

            if (muted || volume == 0) {
                gainControl.setValue(gainControl.getMinimum());
            } else {
                float gain = (float) (20.0 * Math.log10(volume / 100.0));
                gainControl.setValue(gain);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}