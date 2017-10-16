package put.poznan.pl.androidstream.screens.meetup.dagger;

import android.content.Context;
import android.widget.TextView;
import com.hannesdorfmann.annotatedadapter.annotation.ViewField;
import com.hannesdorfmann.annotatedadapter.annotation.ViewType;
import com.hannesdorfmann.annotatedadapter.support.recyclerview.SupportAnnotatedAdapter;
import put.poznan.pl.androidstream.R;
import put.poznan.pl.androidstream.models.RSVP;

import java.util.List;

public class MeetupAdapter extends SupportAnnotatedAdapter implements MeetupAdapterBinder {
    @ViewType(
            layout = R.layout.raw_text,
            views =
            @ViewField(
                    id = R.id.text_item,
                    name = "text",
                    type = TextView.class
            )
    )
    public final int VIEWTYPE_CATEGORY = 0;

    private List<RSVP> items;

    public MeetupAdapter(Context context) {
        super(context);
    }

    @Override
    public int getItemCount() {
        return items == null ? 0 : items.size();
    }

    @Override
    public void bindViewHolder(put.poznan.pl.androidstream.screens.meetup.dagger.MeetupAdapterHolders.VIEWTYPE_CATEGORYViewHolder vh, int position) {
        vh.text.setText(items.get(position).toString());
    }
}
