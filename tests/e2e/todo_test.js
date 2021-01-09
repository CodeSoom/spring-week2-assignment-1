Feature('ToDo');

Scenario('ToDo 제목을 볼 수 있습니다.', ({ I }) => {
  I.amOnPage('/');

  I.see('ToDo');
});

Scenario('ToDo를 등록할 수 있습니다.', ({ I }) => {
  I.amOnPage('/');

  I.fillField('할 일', '강의듣기');
  I.click('추가');
  I.see('강의듣기');
  I.click('완료');
  I.dontSee('강의듣기');
});

Scenario('ToDo를 수정할 수 있습니다.', ({ I }) => {
  I.amOnPage('/');

  I.fillField('할 일', '강의듣기');
  I.click('추가');
  I.click('수정');
  I.fillField('input', '과제하기');
  I.click('확인');
  I.see('과제하기');
  I.click('완료');
  I.dontSee('과제하기');
});

Scenario('ToDo를 수정을 취소할 수 있습니다.', ({ I }) => {
  I.amOnPage('/');

  I.fillField('할 일', '강의듣기');
  I.click('추가');
  I.click('수정');
  I.fillField('input', '과제하기');
  I.click('취소');
  I.click('완료');
  I.dontSee('과제하기');
});
