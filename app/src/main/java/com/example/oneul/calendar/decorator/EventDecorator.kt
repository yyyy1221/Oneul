import com.example.oneul.R
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade

class EventDecorator(var dates:Collection<CalendarDay>):DayViewDecorator{

    private var color:Int

    init {
        color = R.color.primary
        dates = HashSet(dates)
    }

    override fun shouldDecorate(day: CalendarDay?): Boolean {
        TODO("Not yet implemented")
    }

    override fun decorate(view: DayViewFacade?) {
        TODO("Not yet implemented")
    }

}