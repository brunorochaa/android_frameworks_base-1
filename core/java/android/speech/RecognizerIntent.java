/*
 * Copyright (C) 2008 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package android.speech;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;

/**
 * Constants for supporting speech recognition through starting an {@link Intent}
 */
public class RecognizerIntent {
    private RecognizerIntent() {
        // Not for instantiating.
    }

    /**
     * Starts an activity that will prompt the user for speech and sends it through a
     * speech recognizer.  The results will be returned via activity results (in
     * {@link Activity#onActivityResult}, if you start the intent using
     * {@link Activity#startActivityForResult(Intent, int)}), or forwarded via a PendingIntent
     * if one is provided.
     * 
     * <p>Starting this intent with just {@link Activity#startActivity(Intent)} is not supported.
     * You must either use {@link Activity#startActivityForResult(Intent, int)}, or provide a
     * PendingIntent, to receive recognition results.
     * 
     * <p>Required extras:
     * <ul>
     *   <li>{@link #EXTRA_LANGUAGE_MODEL}
     * </ul>
     * 
     * <p>Optional extras:
     * <ul>
     *   <li>{@link #EXTRA_PROMPT}
     *   <li>{@link #EXTRA_LANGUAGE}
     *   <li>{@link #EXTRA_MAX_RESULTS}
     *   <li>{@link #EXTRA_RESULTS_PENDINGINTENT}
     *   <li>{@link #EXTRA_RESULTS_PENDINGINTENT_BUNDLE}
     * </ul>
     * 
     * <p> Result extras (returned in the result, not to be specified in the request):
     * <ul>
     *   <li>{@link #EXTRA_RESULTS}
     * </ul>
     * 
     * <p>NOTE: There may not be any applications installed to handle this action, so you should
     * make sure to catch {@link ActivityNotFoundException}.
     */
    public static final String ACTION_RECOGNIZE_SPEECH = "android.speech.action.RECOGNIZE_SPEECH";

    /**
     * Starts an activity that will prompt the user for speech, sends it through a
     * speech recognizer, and invokes and displays a web search result.
     * 
     * <p>Required extras:
     * <ul>
     *   <li>{@link #EXTRA_LANGUAGE_MODEL}
     * </ul>
     * 
     * <p>Optional extras:
     * <ul>
     *   <li>{@link #EXTRA_PROMPT}
     *   <li>{@link #EXTRA_LANGUAGE}
     *   <li>{@link #EXTRA_MAX_RESULTS}
     * </ul>
     * 
     * <p> Result extras (returned in the result, not to be specified in the request):
     * <ul>
     *   <li>{@link #EXTRA_RESULTS}
     * </ul>
     * 
     * <p>NOTE: There may not be any applications installed to handle this action, so you should
     * make sure to catch {@link ActivityNotFoundException}.
     */
    public static final String ACTION_WEB_SEARCH = "android.speech.action.WEB_SEARCH";

    /**
     * The minimum length of an utterance. We will not stop recording before this amount of time.
     * 
     * Note that it is extremely rare you'd want to specify this value in an intent. If you don't
     * have a very good reason to change these, you should leave them as they are. Note also that
     * certain values may cause undesired or unexpected results - use judiciously! Additionally,
     * depending on the recognizer implementation, these values may have no effect.
     */
    public static final String EXTRA_SPEECH_INPUT_MINIMUM_LENGTH_MILLIS =
            "android.speech.extras.SPEECH_INPUT_MINIMUM_LENGTH_MILLIS";

    /**
     * The amount of time that it should take after we stop hearing speech to consider the input
     * complete. 
     * 
     * Note that it is extremely rare you'd want to specify this value in an intent. If
     * you don't have a very good reason to change these, you should leave them as they are. Note
     * also that certain values may cause undesired or unexpected results - use judiciously!
     * Additionally, depending on the recognizer implementation, these values may have no effect.
     */
    public static final String EXTRA_SPEECH_INPUT_COMPLETE_SILENCE_LENGTH_MILLIS =
            "android.speech.extras.SPEECH_INPUT_COMPLETE_SILENCE_LENGTH_MILLIS";

    /**
     * The amount of time that it should take after we stop hearing speech to consider the input
     * possibly complete. This is used to prevent the endpointer cutting off during very short
     * mid-speech pauses. 
     * 
     * Note that it is extremely rare you'd want to specify this value in an intent. If
     * you don't have a very good reason to change these, you should leave them as they are. Note
     * also that certain values may cause undesired or unexpected results - use judiciously!
     * Additionally, depending on the recognizer implementation, these values may have no effect.
     */
    public static final String EXTRA_SPEECH_INPUT_POSSIBLY_COMPLETE_SILENCE_LENGTH_MILLIS =
            "android.speech.extras.SPEECH_INPUT_POSSIBLY_COMPLETE_SILENCE_LENGTH_MILLIS";

    /**
     * Informs the recognizer which speech model to prefer when performing
     * {@link #ACTION_RECOGNIZE_SPEECH}. The recognizer uses this
     * information to fine tune the results. This extra is required. Activities implementing
     * {@link #ACTION_RECOGNIZE_SPEECH} may interpret the values as they see fit.
     * 
     *  @see #LANGUAGE_MODEL_FREE_FORM
     *  @see #LANGUAGE_MODEL_WEB_SEARCH
     */
    public static final String EXTRA_LANGUAGE_MODEL = "android.speech.extra.LANGUAGE_MODEL";

    /** 
     * Use a language model based on free-form speech recognition.  This is a value to use for 
     * {@link #EXTRA_LANGUAGE_MODEL}. 
     * @see #EXTRA_LANGUAGE_MODEL
     */
    public static final String LANGUAGE_MODEL_FREE_FORM = "free_form";
    /** 
     * Use a language model based on web search terms.  This is a value to use for 
     * {@link #EXTRA_LANGUAGE_MODEL}. 
     * @see #EXTRA_LANGUAGE_MODEL
     */
    public static final String LANGUAGE_MODEL_WEB_SEARCH = "web_search";

    /** Optional text prompt to show to the user when asking them to speak. */
    public static final String EXTRA_PROMPT = "android.speech.extra.PROMPT";

    /**
     * Optional IETF language tag (as defined by BCP 47), for example "en-US". This tag informs the
     * recognizer to perform speech recognition in a language different than the one set in the
     * {@link java.util.Locale#getDefault()}.
     */
    public static final String EXTRA_LANGUAGE = "android.speech.extra.LANGUAGE";

    /** 
     * Optional limit on the maximum number of results to return. If omitted the recognizer
     * will choose how many results to return. Must be an integer.
     */
    public static final String EXTRA_MAX_RESULTS = "android.speech.extra.MAX_RESULTS";

    /**
     * When the intent is {@link #ACTION_RECOGNIZE_SPEECH}, the speech input activity will
     * return results to you via the activity results mechanism.  Alternatively, if you use this
     * extra to supply a PendingIntent, the results will be added to its bundle and the 
     * PendingIntent will be sent to its target.
     */
    public static final String EXTRA_RESULTS_PENDINGINTENT = 
            "android.speech.extra.RESULTS_PENDINGINTENT";
    
    /**
     * If you use {@link #EXTRA_RESULTS_PENDINGINTENT} to supply a forwarding intent, you can
     * also use this extra to supply additional extras for the final intent.  The search results
     * will be added to this bundle, and the combined bundle will be sent to the target.
     */
    public static final String EXTRA_RESULTS_PENDINGINTENT_BUNDLE = 
            "android.speech.extra.RESULTS_PENDINGINTENT_BUNDLE";

    /** Result code returned when no matches are found for the given speech */
    public static final int RESULT_NO_MATCH = Activity.RESULT_FIRST_USER;
    /** Result code returned when there is a generic client error */
    public static final int RESULT_CLIENT_ERROR = Activity.RESULT_FIRST_USER + 1;
    /** Result code returned when the recognition server returns an error */
    public static final int RESULT_SERVER_ERROR = Activity.RESULT_FIRST_USER + 2;
    /** Result code returned when a network error was encountered */
    public static final int RESULT_NETWORK_ERROR = Activity.RESULT_FIRST_USER + 3;
    /** Result code returned when an audio error was encountered */
    public static final int RESULT_AUDIO_ERROR = Activity.RESULT_FIRST_USER + 4;

    /**
     * An ArrayList&lt;String&gt; of the recognition results when performing
     * {@link #ACTION_RECOGNIZE_SPEECH}. Returned in the results; not to be specified in the
     * recognition request. Only present when {@link Activity#RESULT_OK} is returned in
     * an activity result. In a PendingIntent, the lack of this extra indicates failure.
     */
    public static final String EXTRA_RESULTS = "android.speech.extra.RESULTS";
    
    /**
     * Triggers the voice search settings activity.
     */
    public static final String ACTION_VOICE_SEARCH_SETTINGS =
            "android.speech.action.VOICE_SEARCH_SETTINGS";
}
