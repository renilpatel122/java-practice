import React, { useState } from 'react';
import UserInput from './Components/UserInput';
import Report from './Components/Report';

const App = () => {
  const [requestData, setRequestData] = useState(null);

  const handleFormSubmit = (formData) => {
    setRequestData(formData);
  };

  return (
    <div>
      <UserInput onSubmit={handleFormSubmit} />
      {requestData && <Report data={requestData} />}
    </div>
  );
};

export default App;
