import React, { useEffect, useState } from 'react';
import './style.css';

const Report = ({ data }) => {
  console.log("Received Data: ", data);

  const [htmlContent, setHtmlContent] = useState('');
  
  const { company, batch, startDate, endDate, dynamicFields } = data;

  // Convert dynamicFields object into key-value pairs in the request body
  const dynamicFieldEntries = dynamicFields ? Object.entries(dynamicFields) : [];

  // Prepare the request body
  const requestBody = {
    company,
    batch,
    startDate,
    endDate,
    ...dynamicFields // Add dynamic fields to the request body
  };

  useEffect(() => {
    if (batch && company && startDate && endDate) {
      // Assuming you're sending the data in the request body as a map
      console.log("Sending data ",requestBody);
      
      fetch('http://localhost:9090/fetch-data', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(requestBody),
      })
        .then((response) => response.text())  // Assuming HTML content is returned
        .then((data) => setHtmlContent(data))
        .catch((error) => console.error('Error fetching the report:', error));
    }
  }, [batch, company, startDate, endDate, dynamicFields]); // Depend on dynamicFields as well

  return (
    <div className='report'>
      <h1>Cash Audit Report</h1>
      <div>
        {htmlContent && (
          <div dangerouslySetInnerHTML={{ __html: htmlContent }}></div>
        )}
      </div>
    </div>
  );
};

export default Report;
