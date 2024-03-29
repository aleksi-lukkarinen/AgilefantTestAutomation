// This file is part of the GraphWalker java package
// The MIT License
//
// Copyright (c) 2010 graphwalker.org
//
// Permission is hereby granted, free of charge, to any person obtaining a copy
// of this software and associated documentation files (the "Software"), to deal
// in the Software without restriction, including without limitation the rights
// to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
// copies of the Software, and to permit persons to whom the Software is
// furnished to do so, subject to the following conditions:
//
// The above copyright notice and this permission notice shall be included in
// all copies or substantial portions of the Software.
//
// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
// IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
// FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
// AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
// LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
// OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
// THE SOFTWARE.

package org.graphwalker.conditions;

import java.util.Iterator;
import java.util.Vector;

import org.graphwalker.machines.FiniteStateMachine;

public class CombinationalCondition extends StopCondition {

  private Vector<StopCondition> conditions;

  @Override
  public boolean isFulfilled() {
    for (StopCondition condition : conditions) {
      if (!condition.isFulfilled()) {
        return false;
      }
    }
    return true;
  }

  public CombinationalCondition() {
    this.conditions = new Vector<StopCondition>();
  }

  public void add(StopCondition condition) {
    this.conditions.add(condition);
  }

  @Override
  public void setMachine(FiniteStateMachine machine) {
    super.setMachine(machine);
    for (StopCondition condition : conditions) {
      condition.setMachine(machine);
    }
  }

  @Override
  public double getFulfilment() {
    double retur = 0;
    for (StopCondition condition : conditions) {
      retur += condition.getFulfilment();
    }
    return retur / conditions.size();
  }

  @Override
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder("(");
    for (Iterator<StopCondition> i = conditions.iterator(); i.hasNext();) {
      stringBuilder.append(i.next().toString());
      if (i.hasNext()) {
        stringBuilder.append(" AND ");
      }
    }
    stringBuilder.append(")");
    return stringBuilder.toString();
  }

}
