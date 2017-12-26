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
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

public class GoalAdapter extends RecyclerView.Adapter<GoalHolder>{

    private Context c;
    private ArrayList<GoalCard> goalCards;

    private boolean isPast = false;
    private boolean isFuture = false;
    private boolean isToday = false;
    private boolean isTomorrow = false;
    private boolean isYesterday = false;
    private boolean isThisWeek = false;
    private boolean isNextWeek = false;
    private boolean isLastWeek = false;
    private boolean isThisMonth = false;
    private boolean isThisYear = false;
    private boolean isMorning = false;
    private boolean isAfternoon = false;
    private boolean isNight = false;
    private boolean hasMorning = false;
    private boolean hasAfternoon = false;
    private boolean hasNight = false;
    private boolean hasMon = false;
    private boolean hasTue = false;
    private boolean hasWed = false;
    private boolean hasThu = false;
    private boolean hasFri = false;
    private boolean hasSat = false;
    private boolean hasSun = false;

    ArrayList<Boolean> UserDays =  new ArrayList<Boolean>();

    private int UserDay = 0;
    private int NowDay = 0;
    private int UserDate = 0;
    private int NowDate = 0;
    private int UserMonth = 0;
    private int NowMonth = 0;
    private int UserYear = 0;
    private int NowYear = 0;
    private int UserWeekNum = 0;
    private int NowWeekNum = 0;

    private SimpleDateFormat format_user = new SimpleDateFormat("MMMM d yyyy hh 00 aa");
    private SimpleDateFormat std_day = new SimpleDateFormat("EEE, MMM d");
    private SimpleDateFormat std_t = new SimpleDateFormat("h:mm aa");


    private SimpleDateFormat time_in_24 = new SimpleDateFormat("kk");
    private SimpleDateFormat day_number = new SimpleDateFormat("u");
    private SimpleDateFormat day_in_month = new SimpleDateFormat("d");
    private SimpleDateFormat week_number = new SimpleDateFormat("W");
    private SimpleDateFormat week_number_year = new SimpleDateFormat("w");
    private SimpleDateFormat month_number = new SimpleDateFormat("MM");
    private SimpleDateFormat year_number = new SimpleDateFormat("yyyy");


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
        Calendar NowCal = Calendar.getInstance();
        NowCal.setTimeInMillis(now);
        Calendar UserCal = Calendar.getInstance();
        try {
            UserCal.setTime(format_user.parse(goalCards.get(position).getCalcache()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        sortTime(NowCal, UserCal, position);
        holder.titletxt.setText(goalCards.get(position).getTitle());

        switch (goalCards.get(position).getType()){
            case "GG":
                holder.imageView.setImageResource(R.drawable.ic_thumb_up);
                switch (goalCards.get(position).getRepeat()){
                    case "D":
                        holder.subtitletxt.setText(getDailyDisplayString());
                        break;
                    case "W":
                        holder.subtitletxt.setText(getWeeklyDisplayString(UserCal));
                        break;
                    case "M":
                        holder.subtitletxt.setText(getMonthlyDisplayString(position, UserCal));
                        break;
                    case "A":
                        holder.subtitletxt.setText("This is get good!");
                        break;
                }
                break;
            case "QB":
                holder.imageView.setImageResource(R.drawable.ic_pan);
                switch (goalCards.get(position).getRepeat()){
                    case "U":
                        holder.subtitletxt.setText("A fresh start!");
                        break;
                    case "I":
                        holder.subtitletxt.setText("A fresh start!");
                        break;
                    case "N":
                        holder.subtitletxt.setText("A fresh start!");
                        break;
                }
                break;
            case "OR":
                holder.imageView.setImageResource(R.drawable.ic_event_available);
                holder.subtitletxt.setText("This is OR!");
        }
    }

    private void sortTime(Calendar NowCal, Calendar UserCal, int position){
        String dayt = goalCards.get(position).getDaytoggle();
        if (dayt.contains("mo")){hasMorning = true;}
        if (dayt.contains("no")){hasAfternoon = true;}
        if (dayt.contains("ni")){hasNight = true;}

        String weekt = goalCards.get(position).getWeektoggle();
        if (weekt.contains("mon")){hasMon = true;}
        if (weekt.contains("tue")){hasTue = true;}
        if (weekt.contains("wed")){hasWed = true;}
        if (weekt.contains("thu")){hasThu = true;}
        if (weekt.contains("fri")){hasFri = true;}
        if (weekt.contains("sat")){hasSat = true;}
        if (weekt.contains("sun")){hasSun = true;}
        UserDays.add(hasMon);
        UserDays.add(hasTue);
        UserDays.add(hasWed);
        UserDays.add(hasThu);
        UserDays.add(hasFri);
        UserDays.add(hasSat);
        UserDays.add(hasSun);

        int NowTime = Integer.parseInt(time_in_24.format(NowCal.getTime().getTime()));
        if (NowTime <= 10){isMorning = true;} // early morning and morning
        if (NowTime <= 15 && NowTime >10){isAfternoon = true;} // noon and after noon
        if (NowTime <= 24 && NowTime >15){isNight = true;} //late afternoon and night

        NowDay = Integer.parseInt(day_number.format(NowCal.getTime().getTime()));
        UserDate = Integer.parseInt(day_in_month.format(UserCal.getTime().getTime()));
        NowDate = Integer.parseInt(day_in_month.format(NowCal.getTime().getTime()));
        UserWeekNum = Integer.parseInt(week_number.format(UserCal.getTime().getTime()));
        NowWeekNum = Integer.parseInt(week_number.format(NowCal.getTime().getTime()));
        UserMonth = Integer.parseInt(month_number.format(UserCal.getTime().getTime()));
        NowMonth = Integer.parseInt(month_number.format(NowCal.getTime().getTime()));
        UserYear = Integer.parseInt(year_number.format(UserCal.getTime().getTime()));
        NowYear = Integer.parseInt(year_number.format(NowCal.getTime().getTime()));

        isThisWeek = (Integer.parseInt(week_number_year.format(UserCal.getTime().getTime())) == Integer.parseInt(week_number_year.format(NowCal.getTime().getTime())));
        isNextWeek = (Integer.parseInt(week_number_year.format(UserCal.getTime().getTime())) > Integer.parseInt(week_number_year.format(NowCal.getTime().getTime())));
        isLastWeek = (Integer.parseInt(week_number_year.format(UserCal.getTime().getTime())) < Integer.parseInt(week_number_year.format(NowCal.getTime().getTime())));

        if (NowYear == UserYear){
            isThisYear = true;
            if (UserMonth == NowMonth){
                isThisMonth = true;
                if (UserDate == NowDate){
                    isToday = true;
                }
                else if (UserDate - NowDate > 0){
                    isFuture = true;
                    if (UserDate - NowDate == 1){
                        isTomorrow = true;
                    }
                }
                else if (UserDate - NowDate < 0){
                    isPast = true;
                    if (UserDate - NowDate == -1){
                        isYesterday = true;
                    }
                }
            }
            else if (UserMonth > NowMonth){
                isFuture = true;
                if (UserMonth - NowMonth == 1){
                    // if today is the last day of the month and user's date is the first day of the next month
                    if ((NowCal.getInstance().getActualMaximum(NowCal.DAY_OF_MONTH) == NowDate) && (UserDate == 1)){
                        isTomorrow = true;
                    }
                }
            }
            else if (UserMonth < NowMonth){
                isPast = true;
                if (UserMonth - NowMonth == -1){
                    // if today is the first day of the month and user's date is the last day of the previous month
                    if ((UserCal.getInstance().getActualMaximum(UserCal.DAY_OF_MONTH) == UserDate) && (NowDate == 1)){
                        isTomorrow = true;
                    }
                }
            }
        }
        else {
            isFuture = (UserYear > NowYear);
            isPast = (UserYear < NowYear);
        }
    }

    private String getDailyDisplayString(){
        if (isMorning){
            if (hasMorning){
                return "Get prepared: This morning";
            }
            else if (hasAfternoon){
                return "Get prepared: This noon";
            }
            else if (hasNight){
                return "Get prepared:  Tonight";
            }
        }
        else if (isAfternoon){
            if (hasAfternoon){
                return "Get prepared: This noon";
            }
            else if (hasNight){
                return "Get prepared:  Tonight";
            }
            else if (hasMorning){
                return "Get prepared: Tomorrow morning";
            }
        }
        else if (isNight){
            if (hasNight){
                return "Get prepared:  Tonight";
            }
            else if (hasMorning){
                return "Get prepared: Tomorrow morning";
            }
            else if (hasAfternoon){
                return "Get prepared: Tomorrow noon";
            }
        }
        return "";
    }

    private String getWeeklyDisplayString(Calendar UserCal){
        int userdayindex = NowDay - 1;
        int day = 0;
        String this_next = "";
        String time_string = std_t.format(UserCal.getTime().getTime());
        String day_string = "";
        while (userdayindex + 1 < 7){
            if (UserDays.get(userdayindex)){
                day = userdayindex + 1;
                this_next = "This ";
                if (day - NowDay == 1){
                    return "Get prepared: Tomorrow at " + time_string;
                }
                break;
            }
            userdayindex ++;
        }
        if (day == 0){
            userdayindex = 0;
            while (userdayindex + 1 < NowDay){
                if (UserDays.get(userdayindex)){
                    day = userdayindex + 1;
                    this_next = "Next ";
                    break;
                }
                userdayindex ++;
            }
        }
        switch (day){
            case 1:
                day_string = "Monday";
                break;
            case 2:
                day_string = "Tuesday";
                break;
            case 3:
                day_string = "Wednesday";
                break;
            case 4:
                day_string = "Thursday";
                break;
            case 5:
                day_string = "Friday";
                break;
            case 6:
                day_string = "Saturday";
                break;
            case 7:
                day_string = "Sunday";
                break;
        }
        return this_next + day_string + " at " + time_string;
    }

    private String getMonthlyDisplayString(int position, Calendar UserCal){
        String time_string = std_t.format(UserCal.getTime().getTime());
        String date_string = std_day.format(UserCal.getTime().getTime());

        switch (goalCards.get(position).getMonthmode()) {
            case "sd":
                if (UserDate == NowDate){
                    return "Get prepared: Today at " + time_string;
                }
                else if (UserDate - NowDate == 1){
                    return "Get prepared: Tomorrow at " + time_string;
                }
                else {
                    if (isFuture){
                        if (isThisMonth){
                            return date_string + " at " + time_string;
                        }
                    }
                    else if (isPast){
                        Calendar TargetCal = UserCal;
                        TargetCal.set(Calendar.YEAR, NowYear - 1);
                        TargetCal.set(Calendar.MONTH, NowMonth);
                        return std_day.format(TargetCal.getTime().getTime()) + " at " + std_t.format(UserCal.getTime().getTime()) + ", " + Integer.parseInt(year_number.format(TargetCal.getTime().getTime()));
                    }
                }
                break;
            case "xx":
                break;
//                Calendar xxCal = UserCal;
//
//                //xxCal.set(Calendar.YEAR, NowYear - 1);
//                xxCal.set(Calendar.MONTH, NowMonth - 1);
//                xxCal.set(Calendar.DAY_OF_MONTH, 1);
//                int xxFirstDay = Integer.parseInt(day_number.format(xxCal.getTime().getTime()));
//                int xxTargetDay = 0;
//                if (xxFirstDay <= UserDay){
//                    xxTargetDay = 1 + (xxFirstDay - UserDay) + (UserWeekNum - 1) * 7;
//                }
//                else if (xxFirstDay > UserDay){
//                    xxTargetDay = 1 + (xxFirstDay - UserDay) + UserWeekNum * 7;
//                }
//                xxCal.set(Calendar.DAY_OF_MONTH, xxTargetDay);
//                if (xxTargetDay == NowDate){
//                    return "Get prepared: Today at " + time_string;
//                }
//                else if (xxTargetDay - NowDate == 1){
//                    return "Get prepared: Tomorrow at " + time_string;
//                }
//                else {
//                    return std_day.format(xxCal.getTime().getTime()) + " at " + std_t.format(UserCal.getTime().getTime()) + ", " + Integer.parseInt(year_number.format(xxCal.getTime().getTime()));
//                }
        }

        return "";
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
