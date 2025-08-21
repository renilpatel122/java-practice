import React, { useState } from 'react';
import './style.css';

const UserInput = ({ onSubmit }) => {
  const [formData, setFormData] = useState({
    company: '',
    batch: '',
    startDate: '',
    endDate: '',
  });

  // Dropdown options
  const [additionalFields, setAdditionalFields] = useState([]);
  const [selectedOption, setSelectedOption] = useState('');

  const options = ['Currency', 'Customer Name', 'Document Type'];

  const handleDropdownChange = (e) => {
    const value = e.target.value;
    if (value && !additionalFields.includes(value)) {
      setAdditionalFields([...additionalFields, value]);
    }
    setSelectedOption('');
  };

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  const handleAdditionalFieldChange = (e, field) => {
    setFormData({ ...formData, [field]: e.target.value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    
    // Prepare the request body to send
    const requestBody = {
      ...formData,  // Spread form data to include static fields
    };

    // API call
    fetch('http://localhost:9090/fetch-data', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(requestBody),
    })
      .then((response) => response.json())
      .then((data) => {
        console.log('Response:', data);
        // Send form data to parent as well
        onSubmit(formData);
      })
      .catch((error) => console.error('Error:', error));
  };

  return (
    <div className="user">
      <h1>User Input Fields</h1>
      <form onSubmit={handleSubmit}>
        <label>
          Company
          <input
            type="text"
            name="company"
            value={formData.company}
            onChange={handleInputChange}
          />
        </label>
        <label>
          Batch
          <input
            type="text"
            name="batch"
            value={formData.batch}
            onChange={handleInputChange}
          />
        </label>
        <label>
          Date Range
          <div className="date-range">
            <input
              type="text"
              name="startDate"
              placeholder="Start Date"
              value={formData.startDate}
              onChange={handleInputChange}
            />
            <p>To</p>
            <input
              type="text"
              name="endDate"
              placeholder="End Date"
              value={formData.endDate}
              onChange={handleInputChange}
            />
          </div>
        </label>

        {/* Render dynamic input fields */}
        {additionalFields.map((field) => (
          <label key={field}>
            {field}
            <input
              type="text"
              name={field}
              value={formData[field] || ''}
              onChange={(e) => handleAdditionalFieldChange(e, field)}
            />
          </label>
        ))}

        {/* Dropdown to add additional fields */}
        <label>
          Add Additional Field:
          <select value={selectedOption} onChange={handleDropdownChange}>
            <option value="">Select Field</option>
            {options.map((option) => (
              <option key={option} value={option}>
                {option}
              </option>
            ))}
          </select>
        </label>



        <button type="submit">Submit</button>
      </form>
    </div>
  );
};

export default UserInput;
