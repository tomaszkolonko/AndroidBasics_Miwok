package com.example.android.miwok;

/**
 * Simple Word data object for the WordAdapter
 */
public class Word {
    private String mDefaultWord;
    private String mMiwokWord;
    private int mImageResourceId = NO_IMAGE_PROVIDED;
    private static final int NO_IMAGE_PROVIDED = -1;


    private Word() {
        this.mDefaultWord = "";
        this.mMiwokWord = "";
        this.mImageResourceId = 0;
    }
    /**
     * Create a Word object with the default and Miwok translations
     *
     * @param defaultWord
     * @param miwokWord
     */
    public Word(String defaultWord, String miwokWord) {
        this.mDefaultWord = defaultWord;
        this.mMiwokWord = miwokWord;
    }

    /**
     * Create a Word object with the default and Miwok translations and the
     * corresponding image
     *
     * @param defaultWord
     * @param miwokWord
     * @param imageResourceId
     */
    public Word(String defaultWord, String miwokWord, int imageResourceId) {
        this();
        this.mDefaultWord = defaultWord;
        this.mMiwokWord = miwokWord;
        this.mImageResourceId = imageResourceId;
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

    /**
     * Get the reference to the image resource
     *
     * @return
     */
    public int getImageResourceId() {
        return mImageResourceId;
    }

    /**
     * Returns a boolean which says true if there is a reference to an image
     * and false if no reference to an image has been set.
     *
     * @return
     */
    public boolean hasImage() {
        return mImageResourceId != NO_IMAGE_PROVIDED;
    }
}
