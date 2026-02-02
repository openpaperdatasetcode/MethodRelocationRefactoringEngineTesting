package refactoring.test;

protected enum SourceClass<T> {
    INSTANCE;

    private String sourceField = "test";
    private TargetClass<String> target = TargetClass.INSTANCE;

    static class StaticNested {
        String value;
    }

    /**
     * Recursively processes elements
     * @param input input value
     * @return list of processed strings
     */
    List<String> process(T input) {
        StaticNested nested = new StaticNested();
        nested.value = input.toString();
        
        class LocalInner {
            String getValue() {
                return nested.value;
            }
        }
        
        LocalInner local = new LocalInner();
        List<String> result = new ArrayList<>();
        
        do {
            String current = local.getValue();
            result.add(current);
            assert current != null : "Value cannot be null";
        } while (result.size() < 2);
        
        TargetClass.MemberInner inner = target.new MemberInner();
        String callResult = inner.processOverload(sourceField);
        result.add(callResult);
        
        return result;
    }
}

enum TargetClass<T> {
    INSTANCE;

    String targetField = "target";

    class MemberInner {
        String processOverload(String input) {
            return input + targetField;
        }

        String processOverload(Integer input) {
            return input.toString() + targetField;
        }

        String callMethod() {
            try {
                return this.processOverload(targetField);
            } catch (Exception e) {
                return "error";
            }
        }
    }
}
