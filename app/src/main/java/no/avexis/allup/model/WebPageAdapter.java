package no.avexis.allup.model;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import no.avexis.allup.R;

/**
 * Created by Sindre Bøyum on 28.01.2017.
 * https://guides.codepath.com/android/using-the-recyclerview
 */

public class WebPageAdapter extends RecyclerView.Adapter<WebPageAdapter.ViewHolder> {
    private LayoutInflater mInflater;
    private List<WebPage> mWebPages = new ArrayList<>();
    private Context mContext;
    private final PublishSubject<WebPage> onClickSubject = PublishSubject.create();

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView titleTextView;

        public ViewHolder(View itemView) {
            super(itemView);

            titleTextView = (TextView) itemView.findViewById(R.id.webPage_title_view);
        }
    }
    
    public WebPageAdapter(Context context, List<WebPage> webPages) {
        this.mWebPages = webPages;
        this.mInflater = LayoutInflater.from(context);
        this.mContext = context;
    }

//    @Override
//    public int getCount() {
//        return this.webPages.size();
//    }

//    @Override
//    public Object getItem(int position) {
//        return this.mWebPages.get(position);
//    }

    @Override
    public WebPageAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View webPageView = inflater.inflate(R.layout.web_page_item, parent, false);

        ViewHolder viewHolder = new ViewHolder(webPageView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(WebPageAdapter.ViewHolder viewHolder, int position) {
        final WebPage webPage = mWebPages.get(position);

        TextView titleTextView = viewHolder.titleTextView;
        titleTextView.setText(webPage.getTitle());

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickSubject.onNext(webPage);
            }
        });
    }

    public Observable<WebPage> getPositionClicks() {
        return onClickSubject;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return this.mWebPages.size();
    }

//    @Override
//    public View getView(int position, View view, ViewGroup parent) {
//        WebPage webPage = (WebPage) getItem(position);
//        if (view == null) {
//            view = inflater.inflate(R.layout.web_page_item, null);
//        }
//
//        TextView webPageTitle = (TextView) view.findViewById(R.id.webPage_title_view);
//        webPageTitle.setText(webPage.getTitle());
//        return view;
//    }

    public void setWebPages(List<WebPage> webPages) {
        this.mWebPages.addAll(webPages);
        notifyDataSetChanged();
    }

    private Context getContext() {
        return mContext;
    }

    
}
