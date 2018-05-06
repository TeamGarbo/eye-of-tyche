package teamgarbo.github.io.eyeoftyche;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import teamgarbo.github.io.eyeoftyche.Engine.WorldObjects.Items.Item;

/**
 * Created by hercu on 06-May-18.
 */

public class GridAdapter extends BaseAdapter {

    private ArrayList<Item> mItems;
    private int mCount;

    /**
     * Default constructor
     * @param items to fill data to
     */
    public GridAdapter(final ArrayList<Item> items, int rows) {

        mCount = items.size() * rows;
        mItems = new ArrayList<Item>(mCount);
    }

    @Override
    public int getCount() {
        return mCount;
    }

    @Override
    public Object getItem(final int position) {
        return mItems.get(position);
    }

    @Override
    public long getItemId(final int position) {
        return position;
    }

    @Override
    public View getView(final int position, final View convertView, final ViewGroup parent) {

        View view = convertView;

        if (view == null) {
            view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        }

        final TextView text = (TextView) view.findViewById(android.R.id.text1);

        text.setText(mItems.get(position).getName());

        return view;
    }
}
