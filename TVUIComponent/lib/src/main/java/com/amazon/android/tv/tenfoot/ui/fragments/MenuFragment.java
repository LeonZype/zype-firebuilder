package com.amazon.android.tv.tenfoot.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v17.leanback.app.RowsFragment;
import android.support.v17.leanback.widget.ArrayObjectAdapter;
import android.support.v17.leanback.widget.ListRow;
import android.support.v17.leanback.widget.ListRowPresenter;
import android.support.v17.leanback.widget.OnItemViewClickedListener;
import android.support.v17.leanback.widget.OnItemViewSelectedListener;
import android.support.v17.leanback.widget.Presenter;
import android.support.v17.leanback.widget.Row;
import android.support.v17.leanback.widget.RowPresenter;
import android.util.Log;

import com.amazon.android.contentbrowser.ContentBrowser;
import com.amazon.android.model.Action;
import com.amazon.android.tv.tenfoot.presenter.CardPresenter;
import com.amazon.android.tv.tenfoot.presenter.MenuItemPresenter;

import java.util.List;

import static android.support.v17.leanback.widget.FocusHighlight.ZOOM_FACTOR_MEDIUM;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

/**
 * Created by Evgeny Cherkasov on 18.04.2018.
 */

public class MenuFragment extends RowsFragment {
    private static final String TAG = MenuFragment.class.getSimpleName();

    private ArrayObjectAdapter adapter;
    private int selectedMenuItemIndex;

    public interface IMenuFragmentListener {
        void onItemSelected(Action item);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setOnItemViewClickedListener(new ItemViewClickedListener());
        setOnItemViewSelectedListener(new OnItemViewSelectedListener() {
            @Override
            public void onItemSelected(Presenter.ViewHolder itemViewHolder, Object item,
                                       RowPresenter.ViewHolder rowViewHolder, Row row) {
                selectedMenuItemIndex = adapter.indexOf(row);
                Log.i(TAG, "onItemSelected(): item=" + item + ", row=" + row + ", index=" + selectedMenuItemIndex);
            }
        });

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            setupRows();
            getView().requestFocus();
        }
    }

    private void setupRows() {
        List<Action> settings = ContentBrowser.getInstance(getActivity()).getSettingsActions();
        if (settings == null || settings.isEmpty()) {
            Log.d(TAG, "No settings were found");
            return;
        }

        ListRowPresenter presenter = new ListRowPresenter(ZOOM_FACTOR_MEDIUM,false);
        presenter.setShadowEnabled(false);
        presenter.setSelectEffectEnabled(false);
        presenter.setRowHeight(80);
//         ListRowPresenter presenter = new ListRowPresenter();
//         presenter.setRowHeight(100);
        adapter = new ArrayObjectAdapter(presenter);

        final MenuItemPresenter menuItemPresenter = new MenuItemPresenter();

        for (Action item : settings) {
            ArrayObjectAdapter menuItemAdapter = new ArrayObjectAdapter(menuItemPresenter);
            menuItemAdapter.add(item);
            adapter.add(new ListRow(null, menuItemAdapter));
        }

        setAdapter(adapter);
    }

    private final class ItemViewClickedListener implements OnItemViewClickedListener {
        @Override
        public void onItemClicked(Presenter.ViewHolder itemViewHolder, Object item,
                                  RowPresenter.ViewHolder rowViewHolder, Row row) {
            if (item instanceof Action) {
                Action menuAction = (Action) item;
                Log.d(TAG, "Menu item " + menuAction.getAction() + " was clicked");
                ((IMenuFragmentListener) getActivity()).onItemSelected(menuAction);
            }
        }
    }

    public int getSelectedMenuItemIndex() {
        return selectedMenuItemIndex;
    }
}
