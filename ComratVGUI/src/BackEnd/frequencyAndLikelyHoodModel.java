package BackEnd;

import java.text.DecimalFormat;

public class frequencyAndLikelyHoodModel {
	public int firstItemFrequency;
	public int secondItemFrequency;
	public int thirdItemFrequency;
	public String comratVAnswer;

	public double firstItemLikelyhood;
	public double secondItemLikelyhood;
	public double thirdItemLikelyhood;
	public double totalLikelyhood;

	public int firstItemComratVAnswerFrequency;
	public int secondItemComratVAnswerFrequency;
	public int thirdItemComratVAnswerFrequency;
	DecimalFormat df;

	public frequencyAndLikelyHoodModel() {
		firstItemFrequency = 0;
		secondItemFrequency = 0;
		thirdItemFrequency = 0;

		firstItemComratVAnswerFrequency = 0;
		secondItemComratVAnswerFrequency = 0;
		thirdItemComratVAnswerFrequency = 0;

		firstItemLikelyhood = 0;
		secondItemLikelyhood = 0;
		thirdItemLikelyhood = 0;
		totalLikelyhood = 0;
		df=new DecimalFormat("#.####");
	}

	public void calculateTotalLikeyHood() {
		if (this.firstItemComratVAnswerFrequency != 0) {
			this.firstItemLikelyhood = Double.parseDouble(df.format(100*this.firstItemComratVAnswerFrequency
					/ (3 * (double) this.firstItemFrequency)));
		}
		if (this.secondItemComratVAnswerFrequency != 0) {
			this.secondItemLikelyhood = Double.parseDouble(df.format(100*this.secondItemComratVAnswerFrequency
					/ (3 * (double) this.secondItemFrequency)));
		}
		if (this.thirdItemComratVAnswerFrequency != 0) {
			this.thirdItemLikelyhood = Double.parseDouble(df.format(100*this.thirdItemComratVAnswerFrequency
					/ (3 * (double) this.thirdItemFrequency)));
		}
		this.totalLikelyhood = Double.parseDouble(df.format(this.firstItemLikelyhood
				+ this.secondItemLikelyhood + this.totalLikelyhood));
	}

	

}
