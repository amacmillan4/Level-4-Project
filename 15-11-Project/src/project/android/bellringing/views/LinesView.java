package project.android.bellringing.views;

import project.android.bellringing.utilities.Utils;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.TextView;

public class LinesView extends TextView {

	String bell = "2";
	int bells;
		
	public boolean lines = false;
	Paint paint1 = new Paint();
	Paint paint2 = new Paint();

	public LinesView(Context context){
		super(context);
	}

	public LinesView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public LinesView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public void setBell(String bell){
		this.bell = bell;
	}
	
	public void setNumberOfBells(int bell){
		this.bells = bell;
	}
	
	public void clearText(){
		super.setText("");
	}
	
	public void setLimitingText(int bells, String text, int lines){
		
		int characters_allowed = ((bells + 1) * (lines)) - 1;
		
		if (text.length() > characters_allowed){
			text = text.substring(text.length() - characters_allowed, text.length());
			text = text.substring(1 + text.indexOf("\n"), text.length());
		}
		
		super.setText(text);
	}
	
	public void drawLines(boolean lines){
		this.lines  = lines;
		super.setText(super.getText().toString());
	}

	public boolean getdrawLines(){
		return lines;
	}

	@Override
	public void onDraw(Canvas canvas){
		
		if (lines){
			paint2.setAntiAlias(true);
			paint2.setStrokeWidth(8f);
			paint2.setColor(Color.BLUE);

			paint1.setAntiAlias(true);
			paint1.setStrokeWidth(8f);
			paint1.setColor(Color.RED);

			String a = (String) super.getText().toString();

			int index2 = a.indexOf(bell);
			int index1 = a.indexOf("1");
			
			int next2 = 0;
			int next1 = 0;
			
			float y = Utils.dpToPx(16, getContext());

			while (a.length() > bells){
				
				a = a.substring(bells + 1, a.length());

				next2 = a.indexOf(bell);
				next1 = a.indexOf("1");
				
				if (next2 != -1)
					canvas.drawLine(Utils.dpToPx(6f, getContext()) + index2 * 27f, y - Utils.dpToPx(2, getContext()),
							Utils.dpToPx(6f, getContext()) + next2 * 27f, y + Utils.dpToPx(27, getContext()), paint2);
				
				if (next1 != -1)
					canvas.drawLine(Utils.dpToPx(6f, getContext()) + index1 * 27f, y - Utils.dpToPx(2, getContext()),
							 Utils.dpToPx(6f, getContext()) + next1 * 27f, y + Utils.dpToPx(27, getContext()), paint1);
				
				y = y + Utils.dpToPx(27.6f, getContext());
				
				index2 = next2;
				index1 = next1;
				
			}
		}

		super.onDraw(canvas);

	}

}
