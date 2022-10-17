package mx.qsistemas.headhunter.dialogs

import android.content.Context
import android.view.Gravity
import android.widget.RelativeLayout
import android.widget.TextView
import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.LottieDrawable
import com.universe.tvmaze.R
import com.universe.tvmaze.core.utils.dpToPx

class StatusDialog(context: Context, title: String?) :
    BaseDialog(context, R.layout.status_dialog, Gravity.TOP, true) {

    private var title: TextView? = null
    private var lottie: LottieAnimationView? = null
    private val TAG = "StatusDialog"

    init {
        initView()
        setCancelable(false)
        setCanceledOnTouchOutside(false)
        if (title != null) {
            if (title.isNotEmpty()) {
                this.title!!.text = title
            }
        }
    }

    private fun initView(){
        title = findViewById(R.id.txt_status)
        lottie = findViewById(R.id.animation_view)

        val params = lottie!!.layoutParams as RelativeLayout.LayoutParams
        params.setMargins(0, (window!!.decorView.height/3).dpToPx(context), 0, 0)
        lottie!!.layoutParams = params
        lottie!!.repeatMode = LottieDrawable.RESTART
        lottie!!.loop(true)
        lottie!!.imageAssetsFolder = "loader"
        lottie!!.setAnimation(R.raw.loader)
        //lottie!!.setMaxProgress(0.25f)
        lottie!!.playAnimation()
    }
    fun updateTitle(title: String) {
        this.title!!.text = title
    }

    fun dismissSelf() {
        lottie!!.pauseAnimation()
        this.dismiss()
    }
}