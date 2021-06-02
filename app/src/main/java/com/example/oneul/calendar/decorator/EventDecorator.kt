import android.util.Log
import android.widget.Toast
import com.example.oneul.MainActivity.Companion.TAG
import com.example.oneul.R
import com.example.oneul.config.MyContext.Companion.context
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade
import com.prolificinteractive.materialcalendarview.spans.DotSpan

class EventDecorator(var dates:Collection<CalendarDay>):DayViewDecorator{

    private var color:Int
    private var days: HashSet<CalendarDay>

    init {
        color = R.color.primary
        days = HashSet(dates)
    }

    override fun shouldDecorate(day: CalendarDay?): Boolean {
        return days.contains(day)
    }

    override fun decorate(view: DayViewFacade?) {
        view!!.addSpan(DotSpan(5F,color))
    }

}