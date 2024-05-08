package net.onestorm.library.menu.element;

import java.util.Comparator;

public class ElementComparator implements Comparator<Element> {
    @Override
    public int compare(Element element1, Element element2) {
        int priority1 = 0;
        int priority2 = 0;

        if (element1 instanceof PrioritizableElement prioritizableElement) {
            priority1 = prioritizableElement.getPriority();
        }

        if (element2 instanceof PrioritizableElement prioritizableElement) {
            priority2 = prioritizableElement.getPriority();
        }

        return Integer.compare(priority1, priority2);
    }
}
