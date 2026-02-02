package refactoring.test;

abstract class SourceClass {
    protected String sourceField = "source";
    private TargetClass target = new TargetClass();

    class MemberInner {
        String getValue() {
            return target.targetField;
        }
    }

    public List<String> recursiveMethod(int depth) {
        super();
        List<String> result = new ArrayList<>();
        
        if (depth <= 0) {
            return new MemberInner().getValue() != null ? 
                Collections.singletonList(target.targetField) : 
                Collections.emptyList();
        }
        
        try {
            MemberInner inner = new MemberInner();
            result.add(inner.getValue());
            
            Runnable runner = new Runnable() {
                public void run() {
                    result.add(target.targetField);
                }
            };
            runner.run();
            
            List<String> subResult = new SourceClass() {}.recursiveMethod(depth - 1);
            result.addAll(subResult);
        } catch (Exception e) {
            result.add("error");
        }
        
        return result;
    }
}

/**
 * Target class for refactoring test
 */
private class TargetClass {
    String targetField = "target";
}
    