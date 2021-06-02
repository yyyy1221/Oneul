import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import com.example.oneul.MainActivity
import com.example.oneul.R
import com.example.oneul.calendar.decorator.MoodSpan
import com.example.oneul.config.MyContext.Companion.context
import com.example.oneul.model.DiaryDTO
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade
import com.prolificinteractive.materialcalendarview.spans.DotSpan

class DiaryDecorator():DayViewDecorator{

    private var na: DiaryDTO
    private var date: CalendarDay


    init {
        date = CalendarDay.today()
        na = DiaryDTO(date = date, mood = context.getDrawable(R.drawable.joy))
    }

    override fun shouldDecorate(day: CalendarDay?): Boolean {
        Toast.makeText(context,date.toString(), Toast.LENGTH_SHORT).show()
        //Log.d(MainActivity.TAG,"EventDecorator - shouldDecorate() called - "+date.toString())
        return date != null && day!!.equals(date);
    }

    override fun decorate(view: DayViewFacade?) {
        //drawable = context.getDrawable(R.drawable.joy)!!
        view!!.setBackgroundDrawable(na.mood!!)
        view.addSpan(ForegroundColorSpan(ContextCompat.getColor(context,R.color.transparent_blak)));

    }


}