package test;
import java.util.List;import java.util.ArrayList;
interface SourceInterface {private String outerPrivate = "private";
final default List<String> method(TargetInterface target) {List rawList = new ArrayList();rawList.add(outerPrivate);target.action();return rawList;}
final default List<String> method(String str) {return new ArrayList<>();}}
/**
Javadoc for TargetInterface*/public interface TargetInterface {static class NestedClass {}
void action();}