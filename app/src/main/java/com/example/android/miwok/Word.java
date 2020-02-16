package com.example.android.miwok;

/**
 * Simple Word data object for the WordAdapter
 */
public class Word {
    private String mDefaultWord;
    private String mMiwokWord;
    private int mImageResourceId = NO_IMAGE_PROVIDED;
    private int mSoundResourceId = NO_SOUND_PROVIDED;

    private static final int NO_IMAGE_PROVIDED = -1;
    private static final int NO_SOUND_PROVIDED = -1;


    private Word() {
        this.mDefaultWord = "";
        this.mMiwokWord = "";
        this.mImageResourceId = 0;
        this.mSoundResourceId = 0;
    }
    /**
     * Create a Word object with the default and Miwok translations. This constructor
     * is used for the phrases
     *
     * @param defaultWord
     * @param miwokWord
     * @param soundResourceId
     */
    public Word(String defaultWord, String miwokWord, int soundResourceId) {
        this.mDefaultWord = defaultWord;
        this.mMiwokWord = miwokWord;
        this.mSoundResourceId = soundResourceId;
    }

    /**
     * Create a Word object with the default and Miwok translations and the
     * corresponding image
     *
     * @param defaultWord
     * @param miwokWord
     * @param imageResourceId
     * @param soundResourceId
     */
    public Word(String defaultWord, String miwokWord, int imageResourceId, int soundResourceId) {
        this.mDefaultWord = defaultWord;
        this.mMiwokWord = miwokWord;
        this.mImageResourceId = imageResourceId;
        this.mSoundResourceId = soundResourceId;
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
     * Get the reference to the sound resource
     * @return
     */
    public int getSoundResourceId() { return mSoundResourceId; }

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
