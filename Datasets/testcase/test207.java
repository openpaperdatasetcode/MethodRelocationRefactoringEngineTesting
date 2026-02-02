```java
abstract class Source<T> {
    class MemberInner {}
    
    Source() {
        new Object() {};
    }
    
    private void methodToMove(target_inner_rec<T> param) {
        target_inner_rec.StaticNested var = new target_inner_rec.StaticNested();
        int value = var.field;
    }
}

private class target_inner_rec<U> {
    static class StaticNested {
        public int field = 1;
    }
}
```