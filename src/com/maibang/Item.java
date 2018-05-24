/**
 * 
 */
package com.maibang;

/**
 * @author qinfei
 *
 */
public class Item {

	private String text;

	private int score;
	public Item(String text, int score) {
		super();
		this.text = text;
		this.score = score;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return text;
	}

}
