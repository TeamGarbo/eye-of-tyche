package teamgarbo.github.io.eyeoftyche;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.logging.FileHandler;

import teamgarbo.github.io.eyeoftyche.Engine.Room;

/**
 * Created by hercu on 06-May-18.
 */

public class RoomAdapter extends ArrayAdapter<Room>{

    private final Context context;
    private ArrayList<Room> objects = new ArrayList<>();

    public RoomAdapter(Context context, ArrayList<Room> itemArrayList)
    {
        super(context, R.layout.row_item_room, itemArrayList);

        this.context = context;
        this.objects = itemArrayList;
    }


    @Override
    public int getCount()
    {
        return objects.size();
    }

    @Override
    public Room getItem(int i)
    {
        if (getCount() > 0)
            return objects.get(i);
        return null;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent)
    {
        final Room room = objects.get(i);
        final Activity activity = (Activity) context;

        // 1. Create inflater
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // 2. Get rowView from inflater
        View rowView = inflater.inflate(R.layout.row_item_room, parent, false);

        // 3. Get the two text view from the rowView
        TextView labelView = rowView.findViewById(R.id.row_item_name);

        // 4. Set the text for textView
        labelView.setText(room.getDescription());


        // 5. return rowView
        return rowView;
    }
}
