import java.util.List;
import java.util.ArrayList;

class TargetClass {
    // Static nested class (target_inner_rec)
    static class TargetInnerRec {
        private List<String> data;

        TargetInnerRec(List<String> data) {
            this.data = data;
        }

        // Private static method called from source
        private static TargetInnerRec createInstance(List<String> items) {
            return new TargetInnerRec(new ArrayList<>(items));
        }
    }
}

protected class SourceClass<T> {
    protected List<String> outerProtectedList = new ArrayList<>();
    private TargetClass.TargetInnerRec targetInnerField;

    // Anonymous inner class
    private Runnable anonymousAction = new Runnable() {
        @Override
        public void run() {
            outerProtectedList.add("anonymous");
        }
    };

    // Member inner class
    protected class MemberInner {
        void populateList() {
            outerProtectedList.add("member inner");
        }
    }

    // Accessor method to be moved
    private List<String> getValues() {
        // Access outer protected member
        if (outerProtectedList == null) {
            throw new NullPointerException("List is null");
        }

        // Variable call
        new MemberInner().populateList();

        // Array initialization with call to target static method
        TargetClass.TargetInnerRec[] instances = {
            TargetClass.TargetInnerRec.createInstance(outerProtectedList)
        };

        // No new exceptions thrown
        return targetInnerField.data;
    }
}
    