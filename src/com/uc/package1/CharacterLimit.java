package com.uc.package1;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class CharacterLimit extends PlainDocument {
	private int limit;

	public CharacterLimit(int limitation) {
		this.limit = limitation;
	}

	@Override
	public void insertString(int offset, String str, AttributeSet set) throws BadLocationException {
		if (str == null) {
			return;
		} else if ((getLength() + str.length()) <= limit) {
			super.insertString(offset, str, set);
		}
	}
}
