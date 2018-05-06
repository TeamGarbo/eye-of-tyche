package teamgarbo.github.io.eyeoftyche;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import teamgarbo.github.io.eyeoftyche.Engine.Room;
import teamgarbo.github.io.eyeoftyche.Engine.WorldObjects.Items.Item;

/**
 * Created by hercu on 06-May-18.
 */

public class ItemAdapter extends ArrayAdapter<Item> {
    private final Context context;
    private ArrayList<Item> objects = new ArrayList<>();

    public ItemAdapter(Context context, ArrayList<Item> itemArrayList)
    {
        super(context, R.layout.row_item_item, itemArrayList);

        this.context = context;
        this.objects = itemArrayList;
    }


    @Override
    public int getCount()
    {
        return objects.size();
    }

    @Override
    public Item getItem(int i)
    {
        if (getCount() > 0)
            return objects.get(i);
        return null;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent)
    {
        final Item item = objects.get(i);
        final Activity activity = (Activity) context;

        // 1. Create inflater
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // 2. Get rowView from inflater
        View rowView = inflater.inflate(R.layout.row_item_room, parent, false);

        // 3. Get the two text view from the rowView
        TextView labelView = rowView.findViewById(R.id.row_item_name);

        // 4. Set the text for textView
        labelView.setText(item.getName() + " (worth " + item.getCost() + ")");


        // 5. return rowView
        return rowView;
    }
}
