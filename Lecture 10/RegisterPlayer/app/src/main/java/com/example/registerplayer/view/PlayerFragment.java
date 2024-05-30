package com.example.registerplayer.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.registerplayer.R;
import com.example.registerplayer.controller.PlayerController;
import com.example.registerplayer.controller.TeamController;
import com.example.registerplayer.model.Team;
import com.example.registerplayer.model.Player;
import com.example.registerplayer.persistence.PlayerDAO;
import com.example.registerplayer.persistence.TeamDAO;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class PlayerFragment extends Fragment {
    private View view;
    private EditText editTextPlayerId;
    private EditText editTextPlayerName;
    private EditText editTextPlayerBirthDate;
    private EditText editTextPlayerHeight;
    private EditText editTextPlayerWeight;
    private Spinner spinnerTeam;
    private TextView textViewPlayerOutput;
    private PlayerController playerController;
    private TeamController teamController;
    private List<Team> teams;

    public PlayerFragment() {
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_player, container, false);

        editTextPlayerId = view.findViewById(R.id.editTextPlayerId);
        editTextPlayerName = view.findViewById(R.id.editTextPlayerName);
        editTextPlayerBirthDate = view.findViewById(R.id.editTextPlayerBirthDate);
        editTextPlayerHeight = view.findViewById(R.id.editTextPlayerHeight);
        editTextPlayerWeight = view.findViewById(R.id.editTextPlayerWeight);
        spinnerTeam = view.findViewById(R.id.spinnerTeam);
        textViewPlayerOutput = view.findViewById(R.id.textViewPlayerOutput);
        textViewPlayerOutput.setMovementMethod(new ScrollingMovementMethod());

        playerController = new PlayerController(new PlayerDAO(view.getContext()));
        teamController = new TeamController(new TeamDAO(view.getContext()));

        loadSpinner();

        view.findViewById(R.id.buttonPlayerSearch).setOnClickListener(search -> searchEntry());
        view.findViewById(R.id.buttonPlayerRegister).setOnClickListener(register -> registerEntry());
        view.findViewById(R.id.buttonPlayerUpdate).setOnClickListener(update -> updateEntry());
        view.findViewById(R.id.buttonPlayerRemove).setOnClickListener(remove -> removeEntry());
        view.findViewById(R.id.buttonPlayerList).setOnClickListener(list -> listEntry());

        return view;
    }

    private void loadSpinner() {
        Team team0 = new Team(0, "Select Team", "");

        try {
            List<Team> teams = teamController.listEntry();
            teams.add(0, team0);

            ArrayAdapter<Team> arrayAdapter = new ArrayAdapter<>(
                    view.getContext(), android.R.layout.simple_spinner_item, teams);
            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerTeam.setAdapter(arrayAdapter);

        } catch (SQLException e) {
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void searchEntry() {
        Player player;

        try {
            teams = teamController.listEntry();
            player = playerController.searchEntry(
                    new Player(Integer.parseInt(editTextPlayerId.getText().toString())));

            if (player.getPlayerName() != null) {
                readPlayer(player);

            } else {
                Toast.makeText(
                        view.getContext(), "Player not found", Toast.LENGTH_LONG).show();

                clearField();
            }
        } catch (SQLException e) {
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void registerEntry() {
        if (spinnerTeam.getSelectedItemPosition() == 0) {
            Toast.makeText(
                    view.getContext(), "Please select a valid team", Toast.LENGTH_LONG).show();

            return;
        }

        try {
            playerController.registerEntry(writePlayer());

            Toast.makeText(
                    view.getContext(), "Player inserted successfully", Toast.LENGTH_LONG).show();

        } catch (SQLException e) {
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }

        clearField();
    }

    private void updateEntry() {
        if (spinnerTeam.getSelectedItemPosition() == 0) {
            Toast.makeText(
                    view.getContext(), "Please select a valid team", Toast.LENGTH_LONG).show();

            return;
        }

        try {
            playerController.updateEntry(writePlayer());

            Toast.makeText(
                    view.getContext(), "Player updated successfully", Toast.LENGTH_LONG).show();

        } catch (SQLException e) {
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }

        clearField();
    }

    private void removeEntry() {
        try {
            playerController.removeEntry(
                    new Player(Integer.parseInt(editTextPlayerId.getText().toString()))
            );

            Toast.makeText(
                    view.getContext(), "Player removed successfully", Toast.LENGTH_LONG).show();

        } catch (SQLException e) {
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }

        clearField();
    }

    private void listEntry() {
        try {
            List<Player> players = playerController.listEntry();

            StringBuilder stringBuffer = new StringBuilder();

            for (Player player : players) {
                stringBuffer.append(player.toString()).append("\n");
            }

            textViewPlayerOutput.setText(stringBuffer.toString());

        } catch (SQLException e) {
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private Player writePlayer() {
        return new Player(
                Integer.parseInt(editTextPlayerId.getText().toString()),
                editTextPlayerName.getText().toString(),
                LocalDate.parse(editTextPlayerBirthDate.getText().toString()),
                Float.parseFloat(editTextPlayerHeight.getText().toString()),
                Float.parseFloat(editTextPlayerWeight.getText().toString()),
                (Team) spinnerTeam.getSelectedItem()
        );
    }

    private void readPlayer(Player player) {
        editTextPlayerId.setText(String.valueOf(player.getPlayerID()));
        editTextPlayerName.setText(player.getPlayerName());
        editTextPlayerBirthDate.setText(String.valueOf(player.getPlayerBirthDate()));
        editTextPlayerHeight.setText(String.valueOf(player.getPlayerHeight()));
        editTextPlayerWeight.setText(String.valueOf(player.getPlayerWeight()));

        int i = 1;
        for (Team team : teams) {
            if (team.getTeamID() == player.getPlayerTeam().getTeamID()) {
                spinnerTeam.setSelection(i);
            } else {
                i++;
            }
        }
        if (i > teams.size()) {
            spinnerTeam.setSelection(0);
        }

    }

    private void clearField() {
        editTextPlayerId.setText("");
        editTextPlayerName.setText("");
        editTextPlayerBirthDate.setText("");
        editTextPlayerHeight.setText("");
        editTextPlayerWeight.setText("");
        spinnerTeam.setSelection(0);
    }
}