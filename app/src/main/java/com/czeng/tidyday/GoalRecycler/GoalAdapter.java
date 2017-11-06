package com.czeng.tidyday.GoalRecycler;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.czeng.tidyday.GoalDataBase.GoalDataSource;
import com.czeng.tidyday.GoalDataObject.GoalCard;
import com.czeng.tidyday.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class GoalAdapter extends RecyclerView.Adapter<GoalHolder>{

    private Context c;
    private ArrayList<GoalCard> goalCards;

    private SimpleDateFormat time_in_24 = new SimpleDateFormat("kk");
    private SimpleDateFormat day_number = new SimpleDateFormat("u");
    private SimpleDateFormat week_number = new SimpleDateFormat("W");
    private SimpleDateFormat month_number = new SimpleDateFormat("MM");
    private SimpleDateFormat day_in_month = new SimpleDateFormat("dd");
    private SimpleDateFormat sqldate = new SimpleDateFormat("MMMM dddd yyyy KK 00 aa");
    private SimpleDateFormat std_t = new SimpleDateFormat("KK:mm aa");
    private SimpleDateFormat std_day = new SimpleDateFormat("MMM dd");

    public GoalAdapter(Context c, ArrayList<GoalCard> goalCards) {
        this.c = c;
        this.goalCards = goalCards;
    }

    @Override
    public GoalHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card, parent, false);
        GoalHolder holder = new GoalHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(GoalHolder holder, int position) {
        long now = System.currentTimeMillis();
        Calendar cal = Calendar.getInstance();
        try {
            cal.setTime(sqldate.parse(goalCards.get(position).getCalcache()));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        holder.titletxt.setText(goalCards.get(position).getTitle());

        switch (goalCards.get(position).getType()){
            case "GG":
                switch (goalCards.get(position).getRepeat()){
                    case "D":
                        int time_now = Integer.parseInt(time_in_24.format(now));
                        String dayt = goalCards.get(position).getDaytoggle();
                        if (time_now <= 7){
                            if (dayt.contains("mo")){
                                holder.subtitletxt.setText("Get prepared: This morning");
                                break;
                            }
                            else if (dayt.contains("no")){
                                holder.subtitletxt.setText("Get prepared: This noon");
                                break;
                            }
                            else {
                                holder.subtitletxt.setText("Get prepared:  Tonight");
                                break;
                            }
                        }
                        else if (time_now <= 10 && time_now >7){
                            if (dayt.contains("mo")){
                                holder.subtitletxt.setText("Get prepared: Now");
                                break;
                            }
                            else if (dayt.contains("no")){
                                holder.subtitletxt.setText("Get prepared: This noon");
                                break;
                            }
                            else {
                                holder.subtitletxt.setText("Get prepared: Tonight");
                                break;
                            }
                        }
                        else if (time_now <= 15 && time_now >10){
                            if (dayt.contains("no")){
                                holder.subtitletxt.setText("Get prepared: Now");
                                break;
                            }
                            else if (dayt.contains("ni")){
                                holder.subtitletxt.setText("Get prepared: Tonight");
                                break;
                            }
                            else {
                                holder.subtitletxt.setText("Get prepared: Tomorrow Morning");
                                break;
                            }
                        }
                        else if (time_now <= 18 && time_now >15){
                            if (dayt.contains("ni")){
                                holder.subtitletxt.setText("Get prepared: Tonight");
                                break;
                            }
                            else if (dayt.contains("mo")){
                                holder.subtitletxt.setText("Get prepared: Tomorrow Morning");
                                break;
                            }
                            else {
                                holder.subtitletxt.setText("Get prepared: Tomorrow Noon");
                                break;
                            }
                        }
                        else if (time_now <= 24 && time_now >18){
                            if (dayt.contains("ni")){
                                holder.subtitletxt.setText("Get prepared: Now");
                                break;
                            }
                            else if (dayt.contains("mo")){
                                holder.subtitletxt.setText("Get prepared: Tomorrow Morning");
                                break;
                            }
                            else {
                                holder.subtitletxt.setText("Get prepared: Tomorrow Noon");
                                break;
                            }
                        }
                    case "W":
                        int day_now = Integer.parseInt(day_number.format(now));
                        String weekt = goalCards.get(position).getWeektoggle();
                        switch (day_now){
                            case 7:
                                if (weekt.contains("sun")){
                                    holder.subtitletxt.setText("Get prepared: Today at " + std_t.format(cal.getTime().getTime()));
                                    break;
                                }
                                else if (weekt.contains("mon")){
                                    holder.subtitletxt.setText("Get prepared: Tomorrow at " + std_t.format(cal.getTime().getTime()));
                                    break;
                                }
                                else if (weekt.contains("tue")){
                                    holder.subtitletxt.setText("Get prepared: This Tuesday at " + std_t.format(cal.getTime().getTime()));
                                    break;
                                }
                                else if (weekt.contains("wed")){
                                    holder.subtitletxt.setText("Get prepared: This Wednesday at " + std_t.format(cal.getTime().getTime()));
                                    break;
                                }
                                else if (weekt.contains("thu")){
                                    holder.subtitletxt.setText("Get prepared: This Thursday at " + std_t.format(cal.getTime().getTime()));
                                    break;
                                }
                                else if (weekt.contains("fri")){
                                    holder.subtitletxt.setText("Get prepared: This Friday at " + std_t.format(cal.getTime().getTime()));
                                    break;
                                }
                                else {
                                    holder.subtitletxt.setText("Get prepared: This Saturday at " + std_t.format(cal.getTime().getTime()));
                                    break;
                                }
                            case 1:
                                if (weekt.contains("mon")){
                                    holder.subtitletxt.setText("Get prepared: Today at " + std_t.format(cal.getTime().getTime()));
                                    break;
                                }
                                else if (weekt.contains("tue")){
                                    holder.subtitletxt.setText("Get prepared: Tomorrow at " + std_t.format(cal.getTime().getTime()));
                                    break;
                                }
                                else if (weekt.contains("wed")){
                                    holder.subtitletxt.setText("Get prepared: This Wednesday at " + std_t.format(cal.getTime().getTime()));
                                    break;
                                }
                                else if (weekt.contains("thu")){
                                    holder.subtitletxt.setText("Get prepared: This Thursday at " + std_t.format(cal.getTime().getTime()));
                                    break;
                                }
                                else if (weekt.contains("fri")){
                                    holder.subtitletxt.setText("Get prepared: This Friday at " + std_t.format(cal.getTime().getTime()));
                                    break;
                                }
                                else if (weekt.contains("sat")){
                                    holder.subtitletxt.setText("Get prepared: This Saturday at " + std_t.format(cal.getTime().getTime()));
                                    break;
                                }
                                else {
                                    holder.subtitletxt.setText("Get prepared: This Sunday at " + std_t.format(cal.getTime().getTime()));
                                    break;
                                }
                            case 2:
                                if (weekt.contains("tue")){
                                    holder.subtitletxt.setText("Get prepared: Today at " + std_t.format(cal.getTime().getTime()));
                                    break;
                                }
                                else if (weekt.contains("wed")){
                                    holder.subtitletxt.setText("Get prepared: Tomorrow at " + std_t.format(cal.getTime().getTime()));
                                    break;
                                }
                                else if (weekt.contains("thu")){
                                    holder.subtitletxt.setText("Get prepared: This Thursday at " + std_t.format(cal.getTime().getTime()));
                                    break;
                                }
                                else if (weekt.contains("fri")){
                                    holder.subtitletxt.setText("Get prepared: This Friday at " + std_t.format(cal.getTime().getTime()));
                                    break;
                                }
                                else if (weekt.contains("sat")){
                                    holder.subtitletxt.setText("Get prepared: This Saturday at " + std_t.format(cal.getTime().getTime()));
                                    break;
                                }
                                else if (weekt.contains("sun")){
                                    holder.subtitletxt.setText("Get prepared: Next Sunday at " + std_t.format(cal.getTime().getTime()));
                                    break;
                                }
                                else {
                                    holder.subtitletxt.setText("Get prepared: Next Monday at " + std_t.format(cal.getTime().getTime()));
                                    break;
                                }
                            case 3:
                                if (weekt.contains("wed")){
                                    holder.subtitletxt.setText("Get prepared: Today at " + std_t.format(cal.getTime().getTime()));
                                    break;
                                }
                                else if (weekt.contains("thu")){
                                    holder.subtitletxt.setText("Get prepared: Tomorrow at " + std_t.format(cal.getTime().getTime()));
                                    break;
                                }
                                else if (weekt.contains("fri")){
                                    holder.subtitletxt.setText("Get prepared: This Friday at " + std_t.format(cal.getTime().getTime()));
                                    break;
                                }
                                else if (weekt.contains("sat")){
                                    holder.subtitletxt.setText("Get prepared: This Saturday at " + std_t.format(cal.getTime().getTime()));
                                    break;
                                }
                                else if (weekt.contains("sun")){
                                    holder.subtitletxt.setText("Get prepared: Next Sunday at " + std_t.format(cal.getTime().getTime()));
                                    break;
                                }
                                else if (weekt.contains("mo")){
                                    holder.subtitletxt.setText("Get prepared: Next Monday at " + std_t.format(cal.getTime().getTime()));
                                    break;
                                }
                                else {
                                    holder.subtitletxt.setText("Get prepared: Next Tuesday at " + std_t.format(cal.getTime().getTime()));
                                    break;
                                }
                            case 4:
                                if (weekt.contains("thu")){
                                    holder.subtitletxt.setText("Get prepared: Today at " + std_t.format(cal.getTime().getTime()));
                                    break;
                                }
                                else if (weekt.contains("fri")){
                                    holder.subtitletxt.setText("Get prepared: Tomorrow at " + std_t.format(cal.getTime().getTime()));
                                    break;
                                }
                                else if (weekt.contains("sat")){
                                    holder.subtitletxt.setText("Get prepared: This Saturday at " + std_t.format(cal.getTime().getTime()));
                                    break;
                                }
                                else if (weekt.contains("sun")){
                                    holder.subtitletxt.setText("Get prepared: Next Sunday at " + std_t.format(cal.getTime().getTime()));
                                    break;
                                }
                                else if (weekt.contains("mo")){
                                    holder.subtitletxt.setText("Get prepared: Next Monday at " + std_t.format(cal.getTime().getTime()));
                                    break;
                                }
                                else if (weekt.contains("fri")){
                                    holder.subtitletxt.setText("Get prepared: Next Tuesday at " + std_t.format(cal.getTime().getTime()));
                                    break;
                                }
                                else {
                                    holder.subtitletxt.setText("Get prepared: Next Wednesday at " + std_t.format(cal.getTime().getTime()));
                                    break;
                                }
                            case 5:
                                if (weekt.contains("fri")){
                                    holder.subtitletxt.setText("Get prepared: Today at " + std_t.format(cal.getTime().getTime()));
                                    break;
                                }
                                else if (weekt.contains("sat")){
                                    holder.subtitletxt.setText("Get prepared: Tomorrow at " + std_t.format(cal.getTime().getTime()));
                                    break;
                                }
                                else if (weekt.contains("sun")){
                                    holder.subtitletxt.setText("Get prepared: Next Sunday at " + std_t.format(cal.getTime().getTime()));
                                    break;
                                }
                                else if (weekt.contains("mo")){
                                    holder.subtitletxt.setText("Get prepared: Next Monday at " + std_t.format(cal.getTime().getTime()));
                                    break;
                                }
                                else if (weekt.contains("tue")){
                                    holder.subtitletxt.setText("Get prepared: Next Tuesday at " + std_t.format(cal.getTime().getTime()));
                                    break;
                                }
                                else if (weekt.contains("wed")){
                                    holder.subtitletxt.setText("Get prepared: Next Wednesday at " + std_t.format(cal.getTime().getTime()));
                                    break;
                                }
                                else{
                                    holder.subtitletxt.setText("Get prepared: Next Thursday at " + std_t.format(cal.getTime().getTime()));
                                    break;
                                }
                            case 6:
                                if (weekt.contains("sat")){
                                    holder.subtitletxt.setText("Get prepared: Today at " + std_t.format(cal.getTime().getTime()));
                                    break;
                                }
                                else if (weekt.contains("sun")){
                                    holder.subtitletxt.setText("Get prepared: Tomorrow at " + std_t.format(cal.getTime().getTime()));
                                    break;
                                }
                                else if (weekt.contains("mo")){
                                    holder.subtitletxt.setText("Get prepared: Next Monday at " + std_t.format(cal.getTime().getTime()));
                                    break;
                                }
                                else if (weekt.contains("tue")){
                                    holder.subtitletxt.setText("Get prepared: Next Tuesday at " + std_t.format(cal.getTime().getTime()));
                                    break;
                                }
                                else if (weekt.contains("wed")){
                                    holder.subtitletxt.setText("Get prepared: Next Wednesday at " + std_t.format(cal.getTime().getTime()));
                                    break;
                                }
                                else if (weekt.contains("thu")){
                                    holder.subtitletxt.setText("Get prepared: Next Thursday at " + std_t.format(cal.getTime().getTime()));
                                    break;
                                }
                                else{
                                    holder.subtitletxt.setText("Get prepared: Next Friday at " + std_t.format(cal.getTime().getTime()));
                                    break;
                                }
                        }
                        break;
                    case "M":
                        int repeat_day = Integer.parseInt(day_in_month.format(cal.getTime().getTime()));
                        int today = Integer.parseInt(day_in_month.format(now));
                        int repeat_month = Integer.parseInt(month_number.format(cal.getTime().getTime()));
                        int thismonth = Integer.parseInt(month_number.format(now));

                        switch (goalCards.get(position).getMonthmode()){
                            case "sd":
                                if (today == repeat_day){
                                    holder.subtitletxt.setText("Get prepared: Today at " + std_t.format(cal.getTime().getTime()));
                                    break;
                                }
                                else if (today < repeat_day){
                                    holder.subtitletxt.setText(std_day.format(cal.getTime().getTime()) + ", at " + std_t.format(cal.getTime().getTime()));
                                    break;
                                }
                                else {
                                    Calendar new_cal = cal;
                                    new_cal.add(Calendar.MONTH, 1);
                                    holder.subtitletxt.setText(std_day.format(new_cal.getTime().getTime()) + ", at " + std_t.format(new_cal.getTime().getTime()));
                                    break;
                                }
                            case "xx":
                                if (today == repeat_day && thismonth == repeat_month){
                                    holder.subtitletxt.setText("Get prepared: Today at " + std_t.format(cal.getTime().getTime()));
                                    break;
                                }
                                else if ((thismonth < repeat_month ) || (thismonth == repeat_month && today < repeat_day)){
                                    holder.subtitletxt.setText(std_day.format(cal.getTime().getTime()) + ", at " + std_t.format(cal.getTime().getTime()));
                                    break;
                                }
                                else {
                                    int repeat_day_of_week = Integer.parseInt(day_number.format(cal.getTime().getTime()));
                                    int repeat_week_of_month = Integer.parseInt(week_number.format(cal.getTime().getTime()));
                                    Calendar new_cal = cal;
                                    new_cal.add(Calendar.MONTH, 1);
                                    new_cal.set(Calendar.DAY_OF_MONTH, 1);
                                    int new_day_of_week = Integer.parseInt(day_number.format(new_cal.getTime().getTime()));
                                    if (new_day_of_week < repeat_day_of_week){
                                        new_cal.add(Calendar.DATE, repeat_day_of_week - new_day_of_week);
                                        new_cal.add(Calendar.DATE, 7 * (repeat_week_of_month - 1));
                                    }
                                    else if(new_day_of_week > repeat_day_of_week){
                                        new_cal.add(Calendar.DATE, 7 - new_day_of_week);
                                        new_cal.add(Calendar.DATE, repeat_day_of_week);
                                        new_cal.add(Calendar.DATE, 7 * (repeat_week_of_month - 1));
                                    }
                                    else {
                                        new_cal.add(Calendar.DATE, 7 * (repeat_week_of_month - 1));
                                    }
                                    holder.subtitletxt.setText(std_day.format(new_cal.getTime().getTime()) + ", at " + std_t.format(new_cal.getTime().getTime()));
                                    break;
                                }
                        }
                        break;
                    case "A":
                        break;
                    default:
                        break;
                }
                holder.imageView.setImageResource(R.drawable.ic_thumb_up);
                break;
            case "QB":
                holder.imageView.setImageResource(R.drawable.ic_pan);
                break;
            case "OR":
                holder.imageView.setImageResource(R.drawable.ic_event_available);
                break;
            default:
                holder.imageView.setImageResource(R.drawable.tidy_day);
                holder.subtitletxt.setText("You are doing good!");
        }
    }

    @Override
    public int getItemCount() {
        return goalCards.size();
    }


    public void dismissGoalCardByID(int pos){
        int delete_id = goalCards.get(pos).getId();
        GoalDataSource dataSource = new GoalDataSource(c);
        dataSource.open();
        if (dataSource.deleteGoalByID(delete_id)){
            goalCards.remove(pos);
            this.notifyItemRemoved(pos);
        }
        dataSource.close();

    }

     // MOVE
    public void moveGoalCard(int oldpos, int newpos){
        this.notifyItemMoved(oldpos, newpos);
    }
}
