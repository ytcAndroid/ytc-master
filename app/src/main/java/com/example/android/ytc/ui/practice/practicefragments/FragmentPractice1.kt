package com.example.android.ytc.ui.practice.practicefragments

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.android.ytc.R
import com.example.android.ytc.databinding.FragmentPractice1Binding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber


@AndroidEntryPoint
class FragmentPractice1 : Fragment(), RecognitionListener {
    companion object {
        fun createInstance(): FragmentPractice1 {
            return FragmentPractice1()
        }
    }

    private val PERMISSION_GRANTED = PackageManager.PERMISSION_GRANTED
    private val AUDIO_PERMISSION = Manifest.permission.RECORD_AUDIO
    private val PERMISSION_REQUEST_CODE = 100

    lateinit var binding: FragmentPractice1Binding
    val viewModel: FragmentPracticeViewModel by viewModels()

    //Speech recognizer
    private lateinit var speechRecognizer: SpeechRecognizer
    private lateinit var recognizerIntent: Intent

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Timber.d("Got injected from %s", viewModel)
        Timber.d("FRAGMENT CREATED YEAH")

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_practice_1, container, false)
        binding.viewModel = viewModel
        binding.tvSoal.text = "new soal"

        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(context)
        speechRecognizer.setRecognitionListener(this)

        recognizerInit()

        buttonHandler()

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        observeNavigationEvents(viewModel)
    }

    override fun onStop() {
        super.onStop()
        speechRecognizer.destroy()
    }

    private fun observeNavigationEvents(viewModel: FragmentPracticeViewModel) {

    }

    private fun buttonHandler() {
        binding.btnRekam.setOnClickListener {
            binding.btnRekam.visibility = View.INVISIBLE
            binding.btnStopRekam.visibility = View.VISIBLE
            if (isPermissionGranted()) {
                speechRecognizer.startListening(recognizerIntent)
            } else {
                requestAudioPermission()
                /*requestPermissions(
                    arrayOf(Manifest.permission.RECORD_AUDIO),
                    PERMISSION_REQUEST_CODE
                )*/
            }
        }
    }

    /*
     * Init Recognizer
     */

    private fun recognizerInit() {
        recognizerIntent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "us-US")
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_PROMPT, R.string.speech_prompt)
    }

    /*
     * Request permission
     */

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_REQUEST_CODE) {
            Timber.d("PERMISSION RESULT ${grantResults[0]}")
            if (grantResults[0] == PERMISSION_GRANTED) {
                speechRecognizer.startListening(recognizerIntent)
            }
        }
    }

    private fun requestAudioPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(arrayOf(AUDIO_PERMISSION), PERMISSION_REQUEST_CODE)
        }
    }

    private fun isPermissionGranted(): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            context?.let {
                ActivityCompat.checkSelfPermission(
                    it,
                    AUDIO_PERMISSION
                )
            } == PERMISSION_GRANTED
        else return true
    }


    /*
    * #SpeechRecognition
    */

    override fun onReadyForSpeech(p0: Bundle?) {
//        TODO("Not yet implemented")
        Timber.i("Ready for speech")
    }

    override fun onRmsChanged(p0: Float) {
//        TODO("Not yet implemented")
    }

    override fun onBufferReceived(p0: ByteArray?) {
//        TODO("Not yet implemented")
    }

    override fun onPartialResults(p0: Bundle?) {
//        TODO("Not yet implemented")
    }

    override fun onEvent(p0: Int, p1: Bundle?) {
//        TODO("Not yet implemented")
    }

    override fun onBeginningOfSpeech() {
//        TODO("Not yet implemented")
    }

    override fun onEndOfSpeech() {
//        TODO("Not yet implemented")
    }

    override fun onError(error: Int) {
        val errorMessage: String = getErrorText(error)
        Timber.d("Error while listening $errorMessage")
    }

    private fun getErrorText(error: Int): String {
        var message = ""
        message = when (error) {
            SpeechRecognizer.ERROR_AUDIO -> "Audio recording error"
            SpeechRecognizer.ERROR_CLIENT -> "Cllient side error"
            SpeechRecognizer.ERROR_INSUFFICIENT_PERMISSIONS -> "Insufficient permission"
            SpeechRecognizer.ERROR_NETWORK -> "Network error"
            SpeechRecognizer.ERROR_NETWORK_TIMEOUT -> "Network timeout"
            SpeechRecognizer.ERROR_NO_MATCH -> "No match"
            SpeechRecognizer.ERROR_RECOGNIZER_BUSY -> "Recognizer busy"
            SpeechRecognizer.ERROR_SPEECH_TIMEOUT -> "No speech input"
            else -> "Error not recognized"
        }
        return message
    }

    override fun onResults(results: Bundle?) {
        Timber.d("Result received $results")
        binding.btnRekam.visibility = View.VISIBLE
        binding.btnStopRekam.visibility = View.INVISIBLE
        val speechResult = results!!.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
        var text = ""
        if(speechResult != null){
            for (result in speechResult) text = """
                $result
            """.trimIndent()
        }
        binding.tvResult.text = text
    }
}