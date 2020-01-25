package com.example.android.miwok;

/**
 * Simple Word data object for the WordAdapter
 */
public class Word {
    private String mDefaultWord;
    private String mMiwokWord;


    private Word() {
        this.mDefaultWord = "";
        this.mMiwokWord = "";
    }
    /**
     * Create a Word object with the default and Miwok translations
     *
     * @param defaultWord
     * @param miwokWord
     */
    public Word(String defaultWord, String miwokWord) {
        this();
        this.mDefaultWord = defaultWord;
        this.mMiwokWord = miwokWord;
    }

    /**
     * Get the default translation of the word
     *
     * @return
     */
    public String getDefaultTranslation() {
        return mDefaultWord;
    }

    /**
     * Get the Miwok translation of the word
     *
     * @return
     */
    public String getMiwokTranslation() {
        return mMiwokWord;
    }
}
