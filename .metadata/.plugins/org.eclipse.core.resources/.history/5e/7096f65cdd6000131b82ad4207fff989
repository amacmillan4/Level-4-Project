package project.android.bellringing;

import java.util.HashMap;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

public class AudioPlayer {

	private HashMap<String, Integer> sounds = new HashMap<String, Integer>();
	private SoundPool sPool = new SoundPool(Integer.MAX_VALUE, AudioManager.STREAM_MUSIC, 0);

	public AudioPlayer(Context c, boolean handbells){
		
		if (handbells){
			sounds.put("1",sPool.load(c, R.raw.bell72,1));
			sounds.put("2",sPool.load(c, R.raw.bell71,1));
			sounds.put("3",sPool.load(c, R.raw.bell69,1));
			sounds.put("4",sPool.load(c, R.raw.bell67,1));
			sounds.put("5",sPool.load(c, R.raw.bell65,1));
			sounds.put("6",sPool.load(c, R.raw.bell64,1));
			sounds.put("7",sPool.load(c, R.raw.bell62,1));
			sounds.put("8",sPool.load(c, R.raw.bell60,1));
		}
		
	}

	public void play(Context c, String bell) {
		sPool.play(sounds.get(bell), 1, 1, 1, 0, 1f);


	}

}
