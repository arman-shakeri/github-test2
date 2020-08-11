package arman.shakeri.nargemosa.getjson;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.EmployeeViewHolder> {

    private Context context;
    private ArrayList<model> list;

    public Adapter(ArrayList<model> list){

        this.list = list;
    }
    @NonNull
    @Override
    public EmployeeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.employee_item,parent,false);
        return new EmployeeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeViewHolder holder, final int position) {

        //this is just a test
        model model = list.get(position);
        holder.txtJobTitle.setText(model.getJobTitle());
        holder.txtName.setText(model.getName());
        holder.txtEmailAddress.setText(model.getEmailAddress());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                list.remove(position);
                notifyItemRangeChanged(position,list.size());
                notifyItemRemoved(position);
                Toast.makeText(context, "آیتم مورد نظر حذف شد", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class EmployeeViewHolder extends RecyclerView.ViewHolder{

        TextView txtJobTitle,txtName,txtEmailAddress;
        CardView cardView;

        public EmployeeViewHolder(@NonNull View itemView) {
            super(itemView);

            txtJobTitle     = itemView.findViewById(R.id.txt_employeeItem_jobTitle);
            txtName         = itemView.findViewById(R.id.txt_employeeItem_name);
            txtEmailAddress = itemView.findViewById(R.id.txt_employeeItem_emailAddress);
            cardView        = itemView.findViewById(R.id.cardView);
        }
    }
}
