<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>New Feature Announcement</title>
    <style>
        body.email-body {
            font-family: Arial, Helvetica, sans-serif;
        }

        .email-container {
            max-width: 600px;
            margin: 20px auto;
            background-color: #ffffff;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }

        .email-header {
            text-align: center;
            padding-bottom: 20px;
            border-bottom: 2px solid #f0f0f0;
            margin-bottom: 30px;
        }

        .email-title {
            color: #2c3e50;
            font-size: 24px;
            margin: 0;
        }

        .email-content {
            margin-bottom: 30px;
        }

        .email-text {
            margin: 0 0 20px 0;
            font-size: 16px;
            color: #333333 !important;
        }

        .email-button {
            display: inline-block;
            padding: 12px 24px;
            background-color: #3498db;
            color: #ffffff !important;
            text-decoration: none;
            border-radius: 4px;
            margin: 20px 0;
            font-weight: bold;
            text-align: center;
        }

        .email-footer {
            margin-top: 30px;
            padding-top: 20px;
            border-top: 2px solid #f0f0f0;
            font-size: 14px;
            color: #666666;
        }

        .email-contact {
            margin: 0;
        }

        .email-support-link {
            color: #3498db;
            text-decoration: none;
        }
    </style>
</head>
<body class="email-body">
<div class="email-container">
    <div class="email-header">
        <h1 class="email-title">Introducing a New Feature!</h1>
    </div>

    <div class="email-content">
        <p class="email-text">
            We are excited to announce a new feature that we believe will enhance your experience with our platform.
        </p>
        <p class="email-text">
            Our latest feature, <strong>Feature Name</strong>, allows you to do <strong>description of the feature</strong>. This will help you <strong>benefit or improve</strong> your workflow.
        </p>
        <p class="email-text">
            To start using the new feature, simply <strong>follow these steps</strong>:
        </p>
        <ul class="email-text">
            <li>Step 1</li>
            <li>Step 2</li>
            <li>Step 3</li>
        </ul>
        <a href="${featureLink}" class="email-button">Try the New Feature</a>
    </div>

    <div class="email-footer">
        <p class="email-contact">
            If you have any questions, please <a href="mailto:support@example.com" class="email-support-link">contact our support team</a>.
        </p>
    </div>
</div>
</body>
</html>
