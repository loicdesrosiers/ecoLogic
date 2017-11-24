package Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import Entites.QCMUniqueSolution;
import info.iut.ecologic.R;

public class QCMUniqueSolutionAdapter extends RecyclerView.Adapter<QCMUniqueSolutionAdapter.QCMUniqueSolutionViewHolder>{

    private Context mCtx;
    private List<QCMUniqueSolution> questionList;

    public QCMUniqueSolutionAdapter(Context mCtx, List<QCMUniqueSolution> questionList) {
        this.mCtx = mCtx;
        this.questionList = questionList;
    }

    @Override
    public QCMUniqueSolutionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.layout_list_question, null);
        return new QCMUniqueSolutionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(QCMUniqueSolutionViewHolder holder, int position) {
        QCMUniqueSolution qcm = questionList.get(position);
        holder.TVID.setText(qcm.getId()+"");
        holder.TVIntitule.setText(qcm.getIntitule()+"");
        holder.TVBonneRep.setText(qcm.getIdRep()+"");
        holder.TVThematique.setText(qcm.getTheme()+"");
        holder.TVRep1.setText(qcm.getRep1()+"");
        holder.TVRep2.setText(qcm.getRep2()+"");
        holder.TVRep3.setText(qcm.getRep3()+"");
        holder.TVRep4.setText(qcm.getRep4()+"");
        holder.TVExplication.setText(qcm.getExplication()+"");
    }

    @Override
    public int getItemCount() {
        return questionList.size();
    }

    class QCMUniqueSolutionViewHolder extends RecyclerView.ViewHolder{

        TextView TVID, TVThematique, TVBonneRep, TVIntitule, TVRep1, TVRep2, TVRep3, TVRep4, TVExplication;

        public QCMUniqueSolutionViewHolder(View itemView) {
            super(itemView);
            TVID = itemView.findViewById(R.id.TVID);
            TVThematique = itemView.findViewById(R.id.TVThematique);
            TVBonneRep = itemView.findViewById(R.id.TVBonneRep);
            TVIntitule = itemView.findViewById(R.id.ETIntitule);
            TVRep1 = itemView.findViewById(R.id.TVRep1);
            TVRep2 = itemView.findViewById(R.id.TVRep2);
            TVRep3 = itemView.findViewById(R.id.TVRep3);
            TVRep4 = itemView.findViewById(R.id.TVRep4);
            TVExplication = itemView.findViewById(R.id.TVExplication);
        }
    }

}
