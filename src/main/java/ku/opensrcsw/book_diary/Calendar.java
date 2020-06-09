package ku.opensrcsw.book_diary;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

public class Calendar extends JDatePickerImpl {
	Calendar(){
		super(new JDatePanelImpl(new UtilDateModel()));
    }
}
