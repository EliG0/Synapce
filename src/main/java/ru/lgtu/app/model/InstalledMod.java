package ru.lgtu.app.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class InstalledMod {
    private int modId;
    private int priority;
    private List<Integer> conflicts;
    private boolean enabled;

    public InstalledMod() {
        this.conflicts = new ArrayList<>();
        this.enabled = false;
    }

    public InstalledMod(int modId, int priority) {
        this.modId = modId;
        this.priority = priority;
        this.conflicts = new ArrayList<>();
        this.enabled = false;
    }


    public int getModId() {
        return modId;
    }

    public int getPriority() {
        return priority;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public List<Integer> getConflicts() {
        return new ArrayList<>(conflicts);
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void toggleEnabled() {
        this.enabled = !this.enabled;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public void addConflict(int otherModId) {
        if (!conflicts.contains(otherModId)) {
            conflicts.add(otherModId);
        }
    }

    public void removeConflict(int otherModId) {
        conflicts.remove(Integer.valueOf(otherModId));
    }

    public boolean isConflictingWith(int otherModId) {
        return conflicts.contains(otherModId);
    }

    @Override
    public String toString() {
        return "InstalledMod{ID=" + modId + ", enabled=" + enabled + ", priority=" + priority + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InstalledMod that = (InstalledMod) o;
        return modId == that.modId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(modId);
    }
}