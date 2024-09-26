package ru.job4j.generics;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class RoleStoreTest {

    @Test
    void whenAddAndFindRoleIsProgrammer() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Programmer"));
        String expected = "Programmer";
        Role result = store.findById("1");
        assertThat(result.getRolename()).isEqualTo(expected);
    }

    @Test
    void whenAddAndFindRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Dentist"));
        assertThat(store.findById("2")).isNull();
    }

    @Test
    void whenAddDuplicateRoleAndFindProgrammer() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Programmer"));
        store.add(new Role("1", "Dentist"));
        String expected = "Programmer";
        Role result = store.findById("1");
        assertThat(result.getRolename()).isEqualTo(expected);
    }

    @Test
    void whenReplaceRoleThenRoleIsDentist() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Programmer"));
        store.replace("1", new Role("1", "Dentist"));
        Role result = store.findById("1");
        String expected = "Dentist";
        assertThat(result.getRolename()).isEqualTo(expected);
    }

    @Test
    void whenNoReplaceRoleThenNoChangeRole() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Programmer"));
        store.replace("2", new Role("2", "Dentist"));
        Role result = store.findById("1");
        String expected = "Programmer";
        assertThat(result.getRolename()).isEqualTo(expected);
    }

    @Test
    void whenDeleteRoleThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Dentist"));
        store.delete("1");
        Role result = store.findById("1");
        assertThat(result).isNull();
    }

    @Test
    void whenNoDeleteRoleThenRoleIsProgrammer() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Programmer"));
        store.delete("2");
        Role result = store.findById("1");
        String expected = "Programmer";
        assertThat(result.getRolename()).isEqualTo(expected);
    }

    @Test
    void whenReplaceOkThenTrue() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Dentist"));
        boolean result = store.replace("1", new Role("1", "Programmer"));
        assertThat(result).isTrue();
    }

    @Test
    void whenReplaceNotOkThenFalse() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Programmer"));
        boolean result = store.replace("2", new Role("2", "Dentist"));
        assertThat(result).isFalse();
    }
}