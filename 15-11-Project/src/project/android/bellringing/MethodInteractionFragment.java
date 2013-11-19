package project.android.bellringing;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

public class MethodInteractionFragment extends Fragment {

	private final char[] possibleBellNumbering = {'1','2','3','4','5','6','7','8','9','0','E','T','A','B','C','D'};

	//ArrayLists to hold ImageViews and TextViews
	ArrayList<BellImageView> bellImageViews = new ArrayList<BellImageView>();
	ArrayList<TextView> bellNumberTextViews = new ArrayList<TextView>();

	View v;	//Global View
	
	AudioPlayer bellAudio = new AudioPlayer();
	DisplayMethod displayMethodThread = new DisplayMethod();
	boolean isPlaying = false;
	
	Method method = new Method("Aberafan", "&-36-14-58-16-34-58-56-58,12", 100, 8);
	Method methodCopy = method;
	
	Button showButton;

	Button runButton;

	final Button helpButton = null;
	
	Handler mHandler = new Handler();
	LinesView methodView;
	boolean startingRound = false;
	boolean finished = false;
	boolean waitForMe = true;
	double pressTime, playTime;
	TextView txtMethodName, score;

	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setRetainInstance(true);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState){
		v = inflater.inflate(R.layout.fragment_method_interaction, parent, false);

		//Get handle on LinearLayout to display bells
		LinearLayout globalLinearLayout = (LinearLayout) v.findViewById(R.id.globalView);
		globalLinearLayout.setOnClickListener(new View.OnClickListener() {

			//Used for interacting with the bells
			@Override
			public void onClick(View view) {

				if(finished == true){
					
					pressTime = System.currentTimeMillis();
					
					if (playTime != 0){
						score.setText(Math.abs(playTime - pressTime) + "");
					}
						
				}

			}
		});
		
		//Get a handle on the layout
		RelativeLayout relativeLayoutTopScreen = (RelativeLayout) v.findViewById(R.id.l1);
		relativeLayoutTopScreen.setBackgroundColor(Color.WHITE);
		
		//Find out the Width of the Screen
		int widthRelLayout = 350;
		widthRelLayout = dpToPx(widthRelLayout);
		
		int heightRelLayout = widthRelLayout;
		
		int scale = (int) (heightRelLayout/ ((int) (methodCopy.getBells() + 1)/2)) - 20;
		
		//Starting positions
		int pos = ((int) (widthRelLayout/2 - scale*1.2));
		int posX = (int) (heightRelLayout/2 - scale * 0.6);

		//When there are 4 bells the positions need tweaked
		if (methodCopy.getBells() == 4){
			scale = dpToPx(100);
			pos = dpToPx(25);
			posX = dpToPx(110);
		}

		//Starting variables
		double a = 0;
		double b = 0;

		//Display the IMAGEviews
		for (int i = 0; bellImageViews.size() < methodCopy.getBells();  i++ ){

			//Use cos and sin to create circle of ImageViews
			a = pos - pos * Math.sin(Math.toRadians((i) * 180/ (int) ((methodCopy.getBells() - 1 )/ 2)));
			b = posX - posX * Math.cos(Math.toRadians((i) * 180/ (int) ((methodCopy.getBells() - 1 )/ 2)));


			bellImageViews.add(initializeImgViews(getActivity(),(methodCopy.getBells()/2) - i,R.drawable.bell_dl_256,RelativeLayout.ALIGN_PARENT_LEFT,
					(int) a,0,(int) b,scale - (i)));

			//Add textView with same id as ImageView + 20
			TextView t1 = new TextView(getActivity());
			t1.setId(20 + (methodCopy.getBells()/2) - i);
			RelativeLayout.LayoutParams params1 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
			params1.setMargins((int) a - 5 , (int) (b + scale/2), 0, 0);
			t1.setLayoutParams(params1);
			t1.setBackgroundColor(Color.WHITE);
			t1.setText("" + ((methodCopy.getBells()/2) - i));
			bellNumberTextViews.add(t1);


			bellImageViews.add(initializeImgViews(getActivity(),(methodCopy.getBells()/2) + i + 1,R.drawable.bell_dr_256,RelativeLayout.ALIGN_PARENT_RIGHT,
					0,(int) a,(int) b,scale + ((i + 1))));

			//Add textView with same id as ImageView + 20
			TextView t2 = new TextView(getActivity());
			t2.setId(20 + (methodCopy.getBells()/2) + i + 1);
			RelativeLayout.LayoutParams params2 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
			params2.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
			params2.setMargins(0, (int) (b + scale/2), (int) a - 5, 0);
			t2.setLayoutParams(params2);
			t2.setBackgroundColor(Color.WHITE);
			t2.setText("" + ((methodCopy.getBells()/2) + i + 1));
			bellNumberTextViews.add(t2);
		}

		//Set up TextView to display the method
		methodView = (LinesView) v.findViewById(R.id.textView1);
		methodView.setTextSize(24);
		methodView.setText("           ");
		methodView.setTextColor(Color.BLACK);
		methodView.setBackgroundColor(Color.WHITE);
		methodView.setClickable(false);

		//Set up TextView to display the method name
		txtMethodName = new TextView(getActivity());
		RelativeLayout.LayoutParams params1 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
		params1.addRule(RelativeLayout.CENTER_HORIZONTAL);
		params1.addRule(RelativeLayout.CENTER_VERTICAL);
		txtMethodName.setLayoutParams(params1);
		txtMethodName.setBackgroundColor(Color.WHITE);
		txtMethodName.setText(method.getName());

		showButton = (Button) v.findViewById(R.id.btnStop);
		showButton.setBackgroundColor(Color.LTGRAY);
		showButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View view) {

				if (isPlaying == false){
					Intent i = new Intent(getActivity(), MethodShowActivity.class);
					startActivity(i);
				}
				
				isPlaying = false;
				
				for (BellImageView b: bellImageViews)
					b.setClickable(true);
				

			}
		});
		
		//Set-up Button to run the method
		runButton = (Button) v.findViewById(R.id.btnRun);
		runButton.setBackgroundColor(Color.LTGRAY);
		runButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View view) {

				if(startingRound == false)
					startingRound = true;
				else 
					startingRound = false;

				//Start Method
				if (isPlaying == false){
					runButton.setText("Go");
					showButton.setText("Stand");
					displayMethodThread = new DisplayMethod();
					isPlaying = true;
					displayMethodThread.execute();
				}

			}
		});

		
		
		score = (TextView) v.findViewById(R.id.textView2);

		for(BellImageView i : bellImageViews){
			relativeLayoutTopScreen.addView(i);
		}

		for(TextView i : bellNumberTextViews){
			relativeLayoutTopScreen.addView(i);
		}

		relativeLayoutTopScreen.addView(txtMethodName);

		return v;
	}

	public int dpToPx(int dps) {
		final float scale = getActivity().getResources().getDisplayMetrics().density;
		return (int) (dps * scale + 0.5f);
	}

	public class DisplayMethod extends AsyncTask<Void,Void,Void>{


		@Override
		protected synchronized Void doInBackground(Void... arg0) {

			final ArrayList<String> bells = new ArrayList<String>();

			for (char a: possibleBellNumbering){
				bells.add(a + "");
			}

			mHandler.post(new Runnable() {
				@Override
				public void run() {

					methodView.clearText();
					
					//Nullify listeners while playing
					for (BellImageView b: bellImageViews)
						b.setClickable(false);
					
				}
			}); 

			String currentText = "";
			String x = "";
			int i= 0;

			while (!x.equals("\r") && isPlaying){
				
				//Re-initialise scoring variables
				pressTime = 0;
				playTime = 0;

				String n = "";

				if (finished == true){
					n = methodCopy.calcNext() + "";
				}
				else{

					n = methodCopy.start();

					if (startingRound == false && n.equals("Complete")){
						finished = true;
						n = methodCopy.calcNext() + "";
					}
					else if (startingRound == true && n.equals("Complete"))
						n = methodCopy.start();
				}



				final String next = n;

				x = next;

				final String copyX = x;
				if ((currentText.length() - methodCopy.getBells()) % ((methodCopy.getBells()) + 1) == 0 && currentText.length() >= methodCopy.getBells())
					currentText += "\n";

				currentText += x;
				final String cText = currentText;

				mHandler.post(new Runnable() {
					@Override
					public void run() {

						methodView.setBell(bellNumberTextViews.get(bellNumberTextViews.size() - 1).getText().toString());
						methodView.setLimitingText(methodCopy.getBells(), cText, 6);

						BellImageView b = (BellImageView)v.findViewById(bells.indexOf(copyX) + 1);
						b.switchImage((Integer) b.getTag());

						playTime = System.currentTimeMillis();
						bellAudio.play(getActivity(),next);
						
						if (pressTime != 0)
							score.setText(Math.abs(playTime - pressTime) + "");
					
						
						methodView.drawLines(true);
						
						
					}
				}); 

				i++;

				try {
					wait(200);

					if (i % ((methodCopy.getBells()) * 2) == 0)
						wait(200);

				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}



			return null;
		}

	}
	public void swap_TextView(){

		for(int i = 0; i < method.getBells(); i++){
			TextView a =  bellNumberTextViews.get(i);
			a.setText("" + possibleBellNumbering[bellImageViews.get(i).getId()-1]);			
		}
	}

	//Method to swap the location of ImageViews based on where the user clicks
	public void swap_ImageView(int a){

		//Creates 2 copies of the original data
		ArrayList<BellImageView> copy = new ArrayList<BellImageView>(bellImageViews);
		ArrayList<BellImageView> tmp = new ArrayList<BellImageView>(bellImageViews);

		//Keeps switching positions until id is last in the ArrayList (Last means selected bell - BOTTOM RIGHT)
		while (copy.get(copy.size() - 1).getId() != a){

			for(int i = 0; i < tmp.size(); i++){

				if (i == 1){
					copy.set(i, tmp.get(0));
				}
				else if ( i == tmp.size() - 2){
					copy.set(i, tmp.get(tmp.size() - 1));
				}
				else if(i % 2 == 0){
					copy.set(i, tmp.get(i + 2));
				}
				else if(i % 2 == 1){
					copy.set(i, tmp.get(i - 2));
				}
			}

			tmp =  new ArrayList<BellImageView>(copy);

		}

		//Temporary Stores for each margin
		ArrayList<Integer> left = new ArrayList<Integer>();
		ArrayList<Integer> right = new ArrayList<Integer>();
		ArrayList<Integer> top = new ArrayList<Integer>();

		//Stores the margins of the originals in the ArrayList
		for(int i = 0; i < bellImageViews.size(); i++){
			BellImageView x1 = (BellImageView) (bellImageViews.get(i));
			LayoutParams params = (LayoutParams) x1.getLayoutParams();

			left.add(params.leftMargin);
			right.add(params.rightMargin);
			top.add(params.topMargin);		
		}

		//Updates the ImageViews with their new positions based on the margins
		for(int i = 0; i < bellImageViews.size();i++){
			BellImageView x1 = (BellImageView) (copy.get(i));
			LayoutParams params = (LayoutParams) x1.getLayoutParams();

			LayoutParams params1 =  new LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);

			params1.leftMargin = left.remove(0);
			params1.rightMargin = right.remove(0);
			params1.topMargin = top.remove(0);

			//If an ImageView has changed side, update image with bell for correct side
			if(params1.rightMargin == 0){
				params1.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
				x1.setImageResource(R.drawable.bell_dl_256);
			}
			else{
				params1.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
				x1.setImageResource(R.drawable.bell_dr_256);
			}

			params1.height = params.height;
			params1.width = params.height;

			x1.setLayoutParams(params1);
		}

		//Save the changes
		bellImageViews = copy;

	}


	//Method to create the ImageViews and TextViews based on the users input
	public BellImageView initializeImgViews(Activity a,final int id,final int imageResource1,int layoutFeature1,int marginLeft,int marginRight,int marginTop,int height){

		//Create new BellImageView
		final BellImageView im = new BellImageView(a);

		//Set id & image source
		im.setId(id);
		im.setImageResource(imageResource1);

		//Set out Layout
		RelativeLayout.LayoutParams params1 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
		params1.addRule(layoutFeature1);	
		params1.setMargins(marginLeft,marginTop,marginRight,10);
		im.setLayoutParams(params1);

		//Edit Image Height & Width
		im.getLayoutParams().height = height;
		im.getLayoutParams().width = height;

		//Set up Listener
		im.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View view) {

				swap_ImageView(id);
				swap_TextView();


			}
		});

		return im;
	}


}
