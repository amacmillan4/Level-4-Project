package project.android.bellringing.all;

import java.util.HashMap;

import project.android.bellringing.R;
import project.android.bellringing.utilities.Utils;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

public class AudioPlayer {

	private HashMap<String, Integer> sounds = new HashMap<String, Integer>();
	private SoundPool sPool = new SoundPool(100000, AudioManager.STREAM_MUSIC, 0);

	public AudioPlayer(Context c, boolean isHandbells, int numberOfBells){

		if (isHandbells){
			
			//Audio files
			int[] smallScaleHandbells = {R.raw.bell65, R.raw.bell67, R.raw.bell69, R.raw.bell70, R.raw.bell72, R.raw.bell74, R.raw.bell76, R.raw.bell77};
			int[] largeScaleHandbells = {R.raw.bell53,R.raw.bell55,R.raw.bell57,R.raw.bell58,R.raw.bell60,R.raw.bell62,R.raw.bell64,
					R.raw.bell65, R.raw.bell67, R.raw.bell69, R.raw.bell70, R.raw.bell72, R.raw.bell74, R.raw.bell76, R.raw.bell77, R.raw.bell79};

			int j = 0;

			//Use large scale for more than 8 bells
			if (numberOfBells > 8){
				for (int i = numberOfBells; i > 0; i--)
					sounds.put(Utils.bellsToBellNumber(i + ""),sPool.load(c,largeScaleHandbells[j++] ,1));

			}
			
			//Use small scale for 8 or less bells
			else
				for (int i = numberOfBells; i > 0; i--){
					sounds.put(Utils.bellsToBellNumber(i + ""),sPool.load(c,smallScaleHandbells[j++] ,1));
				}
		}
		else{
			
			int[] smallScaleTowerbells = {R.raw.tbell60, R.raw.tbell62, R.raw.tbell64, R.raw.tbell65, R.raw.tbell67, R.raw.tbell69, R.raw.tbell71, R.raw.tbell72};
			int[] largeScaleTowerbells = {R.raw.tbell48, R.raw.tbell50,R.raw.tbell52,R.raw.tbell53,R.raw.tbell55,R.raw.tbell57,R.raw.tbell59,R.raw.tbell60, R.raw.tbell62, 
					R.raw.tbell64, R.raw.tbell65, R.raw.tbell67, R.raw.tbell69, R.raw.tbell71, R.raw.tbell72, R.raw.tbell74};

			int j = 0;

			if (numberOfBells > 8){
				for (int i = numberOfBells; i > 0; i--)
					sounds.put(Utils.bellsToBellNumber(i + ""),sPool.load(c,largeScaleTowerbells[j++] ,1));

			}
			else
			
				for (int i = numberOfBells; i > 0; i--){
					System.out.println(Utils.bellsToBellNumber(i + ""));
					sounds.put(Utils.bellsToBellNumber(i + ""),sPool.load(c,smallScaleTowerbells[j++] ,1));
				}
		}
		
		sounds.put("bob",sPool.load(c,R.raw.bob ,1));
		sounds.put("single",sPool.load(c,R.raw.single ,1));
		sounds.put("rounds",sPool.load(c,R.raw.rounds ,1));
		sounds.put("go",sPool.load(c,R.raw.go ,1));
		sounds.put("stand",sPool.load(c,R.raw.stand ,1));


	}

	public void play(Context c, String bellNumber) {
		sPool.play(sounds.get(bellNumber), 0.7f, 0.7f, 1, 0, 1f);
	}
	
	public void playSound(Context c, String command){

		if(command.equals("bob")) {
        	sPool.play(sounds.get("bob"), 1, 1, 1, 0, 1f);
        } else if(command.equals("single")) {
        	sPool.play(sounds.get("single"), 1, 1, 1, 0, 1f);
        }else if(command.equals("rounds")) {
        	sPool.play(sounds.get("rounds"), 1, 1, 1, 0, 1f);
        }else if(command.equals("go")) {
        	sPool.play(sounds.get("go"), 1, 1, 1, 0, 1f);
        }else if(command.equals("stand")) {
        	sPool.play(sounds.get("stand"), 1, 1, 1, 0, 1f);
        }
            
	}

}
