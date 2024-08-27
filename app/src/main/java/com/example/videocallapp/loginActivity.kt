package com.example.videocallapp

import android.app.Application
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.zegocloud.uikit.prebuilt.call.ZegoUIKitPrebuiltCallService
import com.zegocloud.uikit.prebuilt.call.invite.ZegoUIKitPrebuiltCallInvitationConfig

class loginActivity : AppCompatActivity() {

    lateinit var myuserid: EditText
    lateinit var loginbtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        myuserid = findViewById(R.id.MyUserId)
        loginbtn = findViewById(R.id.login_btn)

        loginbtn.setOnClickListener {
            val myuserid = myuserid.text.toString()
            if (myuserid.isNotEmpty()) {
                val intent = Intent(this@loginActivity, MainActivity::class.java)
                intent.putExtra("UserID", myuserid)
                startActivity(intent)
                setupZegoUIkit(myuserid)
            }
        }
    }

    private fun setupZegoUIkit(UserID: String) {
        val application: Application = application  // Android's application context
        val appID: Long = 1731964920
        val appSign: String = "63382a494c9e6005060a524c69cf524dce859a647f1a76c21a3651f8fac826bc"
        val userName: String = UserID  // Assuming userName is the same as UserID

        val callInvitationConfig = ZegoUIKitPrebuiltCallInvitationConfig()

        // Initialize ZegoUIKit with both userID and userName
        ZegoUIKitPrebuiltCallService.init(
            application,
            appID,
            appSign,
            UserID,        // userID
            userName,      // userName
            callInvitationConfig
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        ZegoUIKitPrebuiltCallService.unInit()
    }
}
