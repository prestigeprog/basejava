/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];

    void clear() {
        for (int i = 0; i < storage.length - 1; i++) {
            if (storage[i] != null) {
                storage[i] = new Resume();
            }
        }
    }

    void save(Resume r) {
        int i = 0;
        if (storage[i] == null) {
            storage[i] = r;
            i++;
        }
    }

    Resume get(String uuid) {
        Resume r = new Resume();
        for (int i = 0; i < storage.length - 1; i++) {
            if (storage[i].uuid.equals(uuid)) {
                storage[i] = r;
                break;
            }
        }
        return r;
    }

    void delete(String uuid) {
        for (int i = 0; i < storage.length - 1; i++) {
            if (storage[i].uuid.equals(uuid)) {
                storage[i] = new Resume();
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] notNullArray = new Resume[storage.length];
        for (int i = 0; i < storage.length - 1; ) {
            if (storage[i] != null) {
                notNullArray[i] = storage[i];
                i++;
            } else {
                i++;
            }
        }
        return notNullArray;
    }

    int size() {
        int i = 0;
        while(true){
            if(storage[i] !=null){
                i++;
                continue;
            } else {
                break;
            }
        }
        return i;
    }
}
