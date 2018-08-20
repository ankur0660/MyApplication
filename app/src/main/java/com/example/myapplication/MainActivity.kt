package com.example.myapplication

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.example.myapplication.databinding.ActivityMainBinding
import io.reactivex.rxjava3.core.Observable

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    var service: MyService? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        var bbc = Bbc().apply { string = "ankur" }
        binding.post = bbc

        Handler(Looper.getMainLooper()).postDelayed({
            binding.post = Bbc().apply { string = "bls" }
            Log.d("ankur", bbc.string)
        }, 2000)


        bindService(Intent(this, MyService::class.java), mConnection, Context.BIND_AUTO_CREATE)

//        var frag1 = BaseFragment.newInstance("frag 1")
//
//        supportFragmentManager.beginTransaction().add(R.id.parent, frag1, "FRAG").addToBackStack(null).commit()
//
//        var frag2 = BaseFragment.newInstance("frag 2")
////
//        supportFragmentManager.beginTransaction().add(R.id.parent, frag2, "FRAG").commit()

//        Handler(HandlerThread("thread1").apply { start() }.looper).post {  bbc().bla() }

    }

    private var mConnection = object : ServiceConnection {
        override fun onServiceConnected(className: ComponentName, service: IBinder) {
            // This is called when the connection with the service has
            // been established, giving us the service object we can use
            // to interact with the service.  Because we have bound to a
            // explicit service that we know is running in our own
            // process, we can cast its IBinder to a concrete class and
            // directly access it.
            this@MainActivity.service = (service as? MyService.LocalBinder)?.getService()
        }

        override fun onServiceDisconnected(className: ComponentName) {
            // This is called when the connection with the service has
            // been unexpectedly disconnected -- that is, its process
            // crashed. Because it is running in our same process, we
            // should never see this happen.
            this@MainActivity.service = null
        }
    }

}

sealed class Abc(){

}
class B : Abc(){
    var d = 10
}

open class C :Abc(){
    var e = 12
}
class  E :C(){

}
var helloWorld = object {
    val hello = "Hello"
    val world = "World"
    // object expressions extend Any, so `override` is required on `toString()`
    override fun toString() = "$hello $world"
}
open class Bbc {

    var string: String = ""
    fun bla() {

        var c = Bbc()
        when{
            c is C ->    {}

        }

        Observable.fromCallable { doAction("0") }
            .map { doAction("5") }
            .doOnComplete { doAction("1") }
            .subscribe {
                doAction("4")
            }
    }


    fun doAction(msg: String) {
        Log.d("ankur", "$msg ${Thread.currentThread().name}")
    }


}
