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
			sounds.put("1",sPool.load(c, R.raw.bell79,1));	//r
			sounds.put("2",sPool.load(c, R.raw.bell77,1));	//d
			sounds.put("3",sPool.load(c, R.raw.bell76,1));	//t
			sounds.put("4",sPool.load(c, R.raw.bell74,1));	//l	
			sounds.put("5",sPool.load(c, R.raw.bell72,1));	//s	
			sounds.put("6",sPool.load(c, R.raw.bell70,1));	//f
			sounds.put("7",sPool.load(c, R.raw.bell69,1));	//m
			sounds.put("8",sPool.load(c, R.raw.bell67,1));	//r
			sounds.put("9",sPool.load(c, R.raw.bell65,1));	//d
			sounds.put("0",sPool.load(c, R.raw.bell64,1));	//t
			sounds.put("E",sPool.load(c, R.raw.bell62,1));	//l
			sounds.put("T",sPool.load(c, R.raw.bell60,1));	//s
			sounds.put("A",sPool.load(c, R.raw.bell58,1));	//f
			sounds.put("B",sPool.load(c, R.raw.bell57,1));	//m
			sounds.put("C",sPool.load(c, R.raw.bell55,1));	//r
			sounds.put("D",sPool.load(c, R.raw.bell53,1));	//d
		}
		else{
			sounds.put("1",sPool.load(c, R.raw.tbell74,1));	//r
			sounds.put("2",sPool.load(c, R.raw.tbell72,1));	//d
			sounds.put("3",sPool.load(c, R.raw.tbell71,1));	//t
			sounds.put("4",sPool.load(c, R.raw.tbell69,1));	//l	
			sounds.put("5",sPool.load(c, R.raw.tbell67,1));	//s	
			sounds.put("6",sPool.load(c, R.raw.tbell65,1));	//f
			sounds.put("7",sPool.load(c, R.raw.tbell64,1));	//m
			sounds.put("8",sPool.load(c, R.raw.tbell62,1));	//r
			sounds.put("9",sPool.load(c, R.raw.tbell60,1));	//d
			sounds.put("0",sPool.load(c, R.raw.tbell59,1));	//t
			sounds.put("E",sPool.load(c, R.raw.tbell57,1));	//l
			sounds.put("T",sPool.load(c, R.raw.tbell55,1));	//s
			sounds.put("A",sPool.load(c, R.raw.tbell53,1));	//f
			sounds.put("B",sPool.load(c, R.raw.tbell52,1));	//m
			sounds.put("C",sPool.load(c, R.raw.tbell50,1));	//r
			sounds.put("D",sPool.load(c, R.raw.tbell48,1));	//d
		}
		
	}

	public void play(Context c, String bell) {
		sPool.play(sounds.get(bell), 1, 1, 1, 0, 1f);


	}

}
