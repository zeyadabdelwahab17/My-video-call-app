package com.example.videocallapp

import android.app.admin.TargetUser
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.zegocloud.uikit.prebuilt.call.invite.widget.ZegoSendCallInvitationButton
import com.zegocloud.uikit.service.defines.ZegoUIKitUser

class MainActivity : AppCompatActivity() {

    private lateinit var targetuserid: EditText
    private lateinit var useridtext: TextView
    private lateinit var videocallbtn: ZegoSendCallInvitationButton
    private lateinit var voicecallbtn: ZegoSendCallInvitationButton



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        targetuserid = findViewById(R.id.targetuser)
        useridtext = findViewById(R.id.userid_txt)
        videocallbtn = findViewById(R.id.videocallbtn)
        voicecallbtn = findViewById(R.id.voicecallbtn)

        val myuserid = intent.getStringExtra("UserID")
        useridtext.text = "Hi $myuserid, \n whom do you want to call?"
        targetuserid.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val targetUserID = targetuserid.text.toString()
                if (targetUserID.isNotEmpty()){
                    startvideocall(targetUserID)
                    startvoicecall(targetUserID)
                }
            }

            override fun afterTextChanged(s: Editable?) {
            }

        })

    }


    private fun startvideocall(targetUserID: String){
        val targetuserName : String = targetUserID

        videocallbtn.setIsVideoCall(true)
        videocallbtn.resourceID = "zego_uikit_call"
        videocallbtn.setInvitees(listOf(ZegoUIKitUser(targetUserID,targetuserName)))
    }



    private fun startvoicecall(targetUserID: String){
        val targetuserName : String = targetUserID

        voicecallbtn.setIsVideoCall(false)
        voicecallbtn.resourceID = "zego_uikit_call"
        voicecallbtn.setInvitees(listOf(ZegoUIKitUser(targetUserID,targetuserName)))
    }
}