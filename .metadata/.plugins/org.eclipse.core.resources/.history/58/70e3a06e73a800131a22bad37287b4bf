package project.android.bellringing.views;

import project.android.bellringing.utilities.Utils;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.TextView;

public class ShowView extends TextView {

	private String bellChoice = "2";
	private int numberOfBells;
	private int displayOptions = 0;
		
	private Paint paint1 = new Paint();
	private Paint paint2 = new Paint();
	private Paint p = new Paint();

	public ShowView(Context context){
		super(context);
	}

	public ShowView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public ShowView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}
	
	/** Sets the bell that will be used alongside the treble:  @param bellChoice*/
	public void setBell(String bellChoice){
		this.bellChoice = bellChoice;
	}
	
	/** Sets the number of bells being played on:  @param numberOfBells*/
	public void setNumberOfBells(int numberOfBells){
		this.numberOfBells = numberOfBells;
	}
	
	public void clearText(){
		super.setText("");
	}
	
	/** Changes the display option */
	public void changeDisplay(){
		displayOptions = (displayOptions + 1) % 2;
		super.setText(super.getText().toString());
	}

	@Override
	public void onDraw(Canvas canvas){
		
		int numberOfLines = getText().length() - getText().toString().replace("\n", "").length();
		
		if (displayOptions == 0 || displayOptions == 1){
			
			if (displayOptions == 0)
				setTextColor(Color.BLACK);
			else if (displayOptions == 1)
				setTextColor(Color.TRANSPARENT);
			
			paint2.setAntiAlias(true);
			paint2.setStrokeWidth(5f);
			paint2.setColor(Color.GREEN);

			paint1.setAntiAlias(true);
			paint1.setStrokeWidth(5f);
			paint1.setColor(Color.RED);

			//Set-up varaibles
			String text = (String) super.getText().toString();
				int index2 = text.indexOf(bellChoice);
			int index1 = text.indexOf("1");
			int next2 = 0;
			int next1 = 0;
			float y = getHeight()/numberOfLines/2;
			float pos = (float) super.getWidth()/((float) numberOfBells * 2f + 1f);


		
			//Loop until all text has been seen
			while (text.length() > numberOfBells){
				
				text = text.substring(numberOfBells + 1, text.length());

				next2 = text.indexOf(bellChoice);
				next1 = text.indexOf("1");
				
				
				//Draw lines based on last position and current position of chosen numbers
				if (next2 != -1)
					canvas.drawLine( (float) (pos + index2 * 2 * pos), y,(float) (pos + next2 * 2 * pos), y + getHeight()/numberOfLines, paint2);
				
				if (next1 != -1)
					canvas.drawLine((float) (pos + index1 * 2 * pos), y,(float) (pos + next1 * 2 * pos), y + getHeight()/numberOfLines, paint1);
				
				y = y + getHeight()/numberOfLines;
				
				index2 = next2;
				index1 = next1;
				
			}
			
			if (displayOptions == 1){
				
				p.setAntiAlias(true);
				p.setStrokeWidth(2f);
				p.setColor(Color.BLACK);

				
				for(float i = 0; i < getHeight() - getHeight()/numberOfLines; i = i + getHeight()/numberOfLines ){
					
					for(float j = 0; j < numberOfBells/2; j++){
						
						float distance = (float) super.getWidth()/((float) numberOfBells * 2f + 1f);
						
						canvas.drawLine(distance * 2 + j * 4 * distance, 10 + i, distance * 2 + j * 4 * distance, 10 + i + getHeight()/numberOfLines/2  , p);
						
					}
										
				}
			}
			
		}

		super.onDraw(canvas);

	}

}
