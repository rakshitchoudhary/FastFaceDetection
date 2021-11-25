package com.tastenkunst.brfv4.brfv4_android_examples;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class BRFv4Fragment extends Fragment {

	ArrayList<String> type;

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		type = new ArrayList<>();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		setHasOptionsMenu(true);
		if (getArguments().getString("type").equalsIgnoreCase("1")) {
			BRFv4View_lips brfView = new BRFv4View_lips(getActivity(), type);
			brfView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
			return brfView;
		} else if (getArguments().getString("type").equalsIgnoreCase("2")) {
			BRFv4View_eyebrow brfView = new BRFv4View_eyebrow(getActivity());
			brfView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
			return brfView;
		} else if (getArguments().getString("type").equalsIgnoreCase("3")) {
			BRFv4View_eyeshadow brfView = new BRFv4View_eyeshadow(getActivity());
			brfView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
			return brfView;
		} else if (getArguments().getString("type").equalsIgnoreCase("4")) {
			BRFv4View_eyeliner brfView = new BRFv4View_eyeliner(getActivity());
			brfView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
			return brfView;
		} else if (getArguments().getString("type").equalsIgnoreCase("5")) {
			BRFv4View_face brfView = new BRFv4View_face(getActivity());
			brfView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
			return brfView;
		} else if (getArguments().getString("type").equalsIgnoreCase("6")) {
			BRFv4View_smile brfView = new BRFv4View_smile(getActivity());
			brfView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
			return brfView;
		} else if (getArguments().getString("type").equalsIgnoreCase("7")) {
			BRFv4View_yawn brfView = new BRFv4View_yawn(getActivity());
			brfView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
			return brfView;
		} else if (getArguments().getString("type").equalsIgnoreCase("8")) {
			BRFv4View_eyeblink brfView = new BRFv4View_eyeblink(getActivity());
			brfView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
			return brfView;
		} else if (getArguments().getString("type").equalsIgnoreCase("0")) {
			BRFv4View brfView = new BRFv4View(getActivity(), getArguments().getString("type"));
			brfView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
			return brfView;
		}
		return null;

	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		inflater.inflate(R.menu.main_menu, menu);
		super.onCreateOptionsMenu(menu, inflater);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.action_lips:
				if (type.contains("lips"))
					type.remove("lips");
				else
					type.add("lips");
				Log.e("Menu", "Lips - " + type.size());
				return true;
			case R.id.action_eyebrow:
				if (type.contains("eyebrow"))
					type.remove("eyebrow");
				else
					type.add("eyebrow");
				Log.e("Menu", "Eyebrow - " + type.size());
				return true;
			case R.id.action_eyeliner:
				if (type.contains("eyeliner"))
					type.remove("eyeliner");
				else
					type.add("eyeliner");
				Log.e("Menu", "Eyeliner - " + type.size());
				return true;
			case R.id.action_face:
				if (type.contains("face"))
					type.remove("face");
				else
					type.add("face");
				Log.e("Menu", "face - " + type.size());
				return true;
			case R.id.action_goggle:
				if (type.contains("goggle"))
					type.remove("goggle");
				else
					type.add("goggle");
				Log.e("Menu", "goggle - " + type.size());
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}
}
