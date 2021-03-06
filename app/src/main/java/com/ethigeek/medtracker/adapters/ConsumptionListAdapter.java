package com.ethigeek.medtracker.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ethigeek.medtracker.utils.Constants;
import com.ethigeek.medtracker.managers.ConsumptionManager;
import com.ethigeek.medtracker.R;
import com.ethigeek.medtracker.utils.ColorGenerator;
import com.ethigeek.medtracker.utils.InitialDrawable;
import com.ethigeek.medtracker.activities.AddOrUpdateConsumptionActivity;
import com.ethigeek.medtracker.daoutils.DBHelper;
import com.ethigeek.medtracker.managers.MedicineManager;

import static com.ethigeek.medtracker.utils.Constants.*;


/**
 * Class for Consumption list processing
 *
 * @author Ethiraj Srinivasan
 */
public class ConsumptionListAdapter extends RecyclerView.Adapter<ConsumptionListAdapter.ConsumptionViewHolder> {

    private Cursor mCursor;
    private Context mContext;
    private Activity mActivity;
    ConsumptionManager consumptionManager;

    private RecyclerView recyclerView;
    private TextView noConsumptions;

    public ConsumptionListAdapter(Context context, Activity activity, Cursor cursor, RecyclerView recyclerView, TextView noConsumptions) {
        this.mContext = context;
        this.mCursor = cursor;
        this.mActivity = activity;
        this.recyclerView = recyclerView;
        this.noConsumptions = noConsumptions;
    }

    /**
     * Method execution while creating UI
     *
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public ConsumptionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Get the RecyclerView item layout
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.consumption_list_item, parent, false);
        return new ConsumptionViewHolder(view);
    }

    /**
     * Method execution while binding UI
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(ConsumptionListAdapter.ConsumptionViewHolder holder, int position) {
        // Move the mCursor to the position of the item to be displayed
        if (!mCursor.moveToPosition(position))
            return; // bail if returned null

        // Update the view holder with the information needed to display
        String medicineName = new MedicineManager().findById(mContext, mCursor.getInt(mCursor.getColumnIndex(DBHelper.CONSUMPTION_KEY_MEDICINEID))).getName();
        String date = mCursor.getString(mCursor.getColumnIndex(DBHelper.CONSUMPTION_KEY_DATE));
        String time = mCursor.getString(mCursor.getColumnIndex(DBHelper.CONSUMPTION_KEY_TIME));
        String dateTime = date + " " + time;
        String quantity = mCursor.getString(mCursor.getColumnIndex(DBHelper.CONSUMPTION_KEY_QUANTITY));

        final int id = mCursor.getInt(mCursor.getColumnIndex(DBHelper.CONSUMPTION_KEY_ID));
        holder.textMedicineName.setText(medicineName);
        holder.textDateTime.setText(dateTime);
        holder.textQuantity.setText(quantity);
        holder.itemView.setTag(id);

        holder.editIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, AddOrUpdateConsumptionActivity.class);
                Bundle b = new Bundle();
                b.putString(ACTION, EDIT);
                b.putInt(ID, id);
                intent.putExtras(b);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
            }
        });

        holder.deleteIcon.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlertDialog.Builder warningDialog = new AlertDialog.Builder(mActivity, R.style.AppTheme_Dialog);
                warningDialog.setTitle(Constants.TITLE_WARNING);
                warningDialog.setMessage(R.string.warning_delete);
                warningDialog.setPositiveButton(Constants.BUTTON_YES, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface alert, int which) {
                        //remove from DB
                        consumptionManager = new ConsumptionManager();
                        consumptionManager.findById(mContext, id);
                        new DeleteConsumption().execute();
                        alert.dismiss();
                    }
                });
                warningDialog.setNegativeButton(Constants.BUTTON_NO, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface alert, int which) {
                        alert.dismiss();
                    }
                });
                warningDialog.show();
            }
        });


        ColorGenerator generator = ColorGenerator.MATERIAL; // or use DEFAULT
        // generate random color
        int color = generator.getRandomColor();
        InitialDrawable drawable = InitialDrawable.builder().buildRound(medicineName.toUpperCase().substring(0, 1), color);
        holder.icon.setImageDrawable(drawable);
    }

    private class DeleteConsumption extends AsyncTask<Void, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Void... params) {
            return consumptionManager.delete(mContext) == -1;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            //update the list
            swapCursor(ConsumptionManager.findAll(mContext));
            if(!result)
              Toast.makeText(mContext, R.string.delete_success, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * @return
     */
    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }

    /**
     * Swaps the Cursor currently held in the adapter with a new one
     * and triggers a UI refresh
     *
     * @param newCursor the new cursor that will replace the existing one
     */
    public void swapCursor(Cursor newCursor) {
        // Always close the previous mCursor first
        if (mCursor != null) mCursor.close();
        mCursor = newCursor;
        if (newCursor != null) {
            // Force the RecyclerView to refresh
            this.notifyDataSetChanged();
        }

        noConsumptions.setVisibility((this.getItemCount() == 0) ? View.VISIBLE : View.GONE);
        recyclerView.setVisibility((this.getItemCount() == 0) ? View.GONE : View.VISIBLE);
    }

    class ConsumptionViewHolder extends RecyclerView.ViewHolder {

        TextView textMedicineName, textDateTime, textQuantity;
        ImageView icon, editIcon, deleteIcon;

        public ConsumptionViewHolder(View itemView) {
            super(itemView);
            textMedicineName = (TextView) itemView.findViewById(R.id.consumptionMedicineName);
            textDateTime = (TextView) itemView.findViewById(R.id.consumptionDateTime);
            textQuantity = (TextView) itemView.findViewById(R.id.consumptionQuantity);
            icon = (ImageView) itemView.findViewById(R.id.consumptionImageIcon);
            editIcon = (ImageView) itemView.findViewById(R.id.editIcon);
            deleteIcon = (ImageView) itemView.findViewById(R.id.deleteIcon);
        }
    }
}
