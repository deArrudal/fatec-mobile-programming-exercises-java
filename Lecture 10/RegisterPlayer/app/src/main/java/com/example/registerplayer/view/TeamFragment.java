package com.example.registerplayer.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.registerplayer.R;
import com.example.registerplayer.controller.TeamController;
import com.example.registerplayer.model.Team;
import com.example.registerplayer.persistence.TeamDAO;

import java.sql.SQLException;
import java.util.List;

public class TeamFragment extends Fragment {
    private View view;
    private EditText editTextTeamId;
    private EditText editTextTeamName;
    private EditText editTextTeamCity;
    private TextView textViewTeamOutput;
    private TeamController teamController;

    public TeamFragment() {
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_team, container, false);

        editTextTeamId = view.findViewById(R.id.editTextTeamId);
        editTextTeamName = view.findViewById(R.id.editTextTeamName);
        editTextTeamCity = view.findViewById(R.id.editTextTeamCity);
        textViewTeamOutput = view.findViewById(R.id.textViewTeamOutput);
        textViewTeamOutput.setMovementMethod(new ScrollingMovementMethod());

        teamController = new TeamController(new TeamDAO(view.getContext()));

        view.findViewById(R.id.buttonTeamSearch).setOnClickListener(search -> searchEntry());
        view.findViewById(R.id.buttonTeamRegister).setOnClickListener(register -> registerEntry());
        view.findViewById(R.id.buttonTeamUpdate).setOnClickListener(update -> updateEntry());
        view.findViewById(R.id.buttonTeamRemove).setOnClickListener(remove -> removeEntry());
        view.findViewById(R.id.buttonTeamList).setOnClickListener(list -> listEntry());

        return view;
    }

    private void searchEntry() {
        Team team;

        try {
            team = teamController.searchEntry(
                    new Team(Integer.parseInt(editTextTeamId.getText().toString())));

            if (team.getTeamName() != null) {
                readTeam(team);

            } else {
                Toast.makeText(
                        view.getContext(), "Team not found", Toast.LENGTH_LONG).show();

                clearField();
            }
        } catch (SQLException e) {
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void registerEntry() {
        try {
            teamController.registerEntry(writeTeam());

            Toast.makeText(
                    view.getContext(), "Team inserted successfully", Toast.LENGTH_LONG).show();

        } catch (SQLException e) {
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }

        clearField();
    }

    private void updateEntry() {
        try {
            teamController.updateEntry(writeTeam());

            Toast.makeText(
                    view.getContext(), "Team updated successfully", Toast.LENGTH_LONG).show();

        } catch (SQLException e) {
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }

        clearField();
    }

    private void removeEntry() {
        try {
            teamController.removeEntry(
                    new Team(Integer.parseInt(editTextTeamId.getText().toString())));

            Toast.makeText(
                    view.getContext(), "Team removed successfully", Toast.LENGTH_LONG).show();

        } catch (SQLException e) {
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }

        clearField();
    }

    private void listEntry() {
        try {
            List<Team> teams = teamController.listEntry();

            StringBuilder stringBuffer = new StringBuilder();

            for (Team team : teams) {
                stringBuffer.append(team.toString()).append("\n");
            }

            textViewTeamOutput.setText(stringBuffer.toString());

        } catch (SQLException e) {
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private Team writeTeam() {
        return new Team(
                Integer.parseInt(editTextTeamId.getText().toString()),
                editTextTeamName.getText().toString(),
                editTextTeamCity.getText().toString()
        );
    }

    private void readTeam(Team team) {
        editTextTeamId.setText(String.valueOf(team.getTeamID()));
        editTextTeamName.setText(team.getTeamName());
        editTextTeamCity.setText(team.getTeamCity());
    }

    private void clearField() {
        editTextTeamId.setText("");
        editTextTeamName.setText("");
        editTextTeamCity.setText("");
    }
}