package ru.weblokos.mvpcryproportfolio.View;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.vsa.paperknife.CellDataProvider;
import com.vsa.paperknife.CellElement;
import com.vsa.paperknife.CellListenerProvider;
import com.vsa.paperknife.CellViewHolder;
import com.vsa.paperknife.DataTarget;
import com.vsa.paperknife.ListenerTarget;
import com.vsa.paperknife.PaperKnife;

import java.util.List;

import ru.weblokos.mvpcryproportfolio.R;

/**
 * Created by gravitas on 11.07.2018.
 */

public class CurrencyAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mInflater;
    private List<? extends CellElement> mList;
    private PaperKnife mPaperKnife;

    public CurrencyAdapter (Context context, List<? extends CellElement> list, CellDataProvider cellDataProvider, CellListenerProvider cellListenerProvider) {
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
        mList = list;
        mPaperKnife = new PaperKnife(cellDataProvider, cellListenerProvider);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public CellElement getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null) {
            convertView = mInflater.inflate(R.layout.item_currency, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        mPaperKnife.bind(mList.get(position), viewHolder);
        return convertView;
    }

    private static class ViewHolder implements CellViewHolder {

        private TextView mTextViewName;
        private TextView mTextViewCost;
        private TextView mTextViewPercent;

        public ViewHolder(View view) {
            mTextViewName = (TextView) view.findViewById(R.id.labelShortName);
            mTextViewCost = (TextView) view.findViewById(R.id.labelCost);
            mTextViewPercent = (TextView) view.findViewById(R.id.labelPercent);
        }

        @DataTarget("Name")
        public void setName(String name) {
            mTextViewName.setText(name);
        }

        @DataTarget("Cost")
        public void setCost(String cost) {
            mTextViewCost.setText(cost);
        }

        @DataTarget("Percent")
        public void setPercent(String percent) {
            mTextViewPercent.setText(percent);
        }

        @DataTarget("PercentColor")
        public void setPercentColor(int color) {
            mTextViewPercent.setTextColor(color);
        }

    }

}
